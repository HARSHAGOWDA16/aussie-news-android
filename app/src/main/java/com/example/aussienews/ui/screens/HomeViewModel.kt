package com.example.aussienews.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aussienews.data.model.Article
import com.example.aussienews.data.remote.ApiConfig
import com.example.aussienews.data.remote.RetrofitClient
import com.example.aussienews.data.repository.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val repository = NewsRepository(RetrofitClient.api)

    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles: StateFlow<List<Article>> = _articles

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    init {
        loadNews()
    }

    private fun loadNews() {
        viewModelScope.launch {
            _loading.value = true
            try {
                _articles.value = repository.getTopHeadlines(ApiConfig.NEWS_API_KEY)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _loading.value = false
            }
        }
    }
}