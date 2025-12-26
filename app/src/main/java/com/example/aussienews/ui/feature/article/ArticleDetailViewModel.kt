package com.example.aussienews.ui.feature.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArticleDetailViewModel : ViewModel() {

    private val _summaryState =
        MutableStateFlow<ArticleSummaryState>(ArticleSummaryState.Idle)
    val summaryState: StateFlow<ArticleSummaryState> = _summaryState

    fun generateSummary(title: String, description: String?) {
        viewModelScope.launch {
            _summaryState.value = ArticleSummaryState.Loading

            // ⏳ TEMP: simulate AI call
            delay(1500)

            val fakeSummary = """
                • $title highlights key developments
                • Main impact focuses on Australia
                • Further implications expected
            """.trimIndent()

            _summaryState.value = ArticleSummaryState.Success(fakeSummary)
        }
    }
}
