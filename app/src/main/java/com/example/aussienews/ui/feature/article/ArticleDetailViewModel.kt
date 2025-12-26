package com.example.aussienews.ui.feature.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class SummaryUiState {
    object Idle : SummaryUiState()
    object Loading : SummaryUiState()
    data class Success(val summary: String) : SummaryUiState()
    data class Error(val message: String) : SummaryUiState()
}

class ArticleDetailViewModel : ViewModel() {

    private val _summaryState =
        MutableStateFlow<SummaryUiState>(SummaryUiState.Idle)
    val summaryState: StateFlow<SummaryUiState> = _summaryState

    fun generateSummary(title: String, description: String?) {
        viewModelScope.launch {
            _summaryState.value = SummaryUiState.Loading

            // 🔹 TEMP: simulate AI call
            delay(1500)

            _summaryState.value = SummaryUiState.Success(
                summary = """
                • This article discusses key developments related to "$title".
                • It highlights recent events and their potential impact.
                • The news is relevant in the current Australian context.
                """.trimIndent()
            )
        }
    }
}
