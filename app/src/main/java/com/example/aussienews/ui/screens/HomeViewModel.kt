package com.example.aussienews.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aussienews.data.model.Article
import com.example.aussienews.data.remote.ApiConfig
import com.example.aussienews.data.remote.RetrofitClient
import com.example.aussienews.data.repository.NewsRepository
import com.example.aussienews.ui.model.NewsCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val repository = NewsRepository(RetrofitClient.api)

    private val _selectedCategory =
        MutableStateFlow(NewsCategory.GENERAL)
    val selectedCategory = _selectedCategory.asStateFlow()

    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles: StateFlow<List<Article>> = _articles

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    init {
        loadNews(NewsCategory.GENERAL)
    }

    fun onCategorySelected(category: NewsCategory) {
        if (_selectedCategory.value != category) {
            _selectedCategory.value = category
            loadNews(category)
        }
    }

    private fun loadNews(category: NewsCategory = NewsCategory.GENERAL) {
        viewModelScope.launch {
            _loading.value = true
            try {
                _articles.value = repository.getTopHeadlines(
                    apiKey = ApiConfig.NEWS_API_KEY,
                    category = category.name.lowercase()
                )
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _loading.value = false
            }
        }
    }
}