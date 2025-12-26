package com.example.aussienews.ui.feature.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aussienews.data.repository.AiSummaryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ArticleDetailViewModel(
    private val repository: AiSummaryRepository
) : ViewModel() {

    private val _summary = MutableStateFlow<String?>(null)
    val summary = _summary.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    fun generateSummary(text: String) {
        viewModelScope.launch {
            _loading.value = true
            try {
                _summary.value = repository.summarize(text)
            } catch (e: Exception) {
                _summary.value = "Failed to generate summary"
            } finally {
                _loading.value = false
            }
        }
    }
}
