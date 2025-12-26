package com.example.aussienews.ui.feature.article

sealed interface ArticleSummaryState {
    object Idle : ArticleSummaryState
    object Loading : ArticleSummaryState
    data class Success(val summary: String) : ArticleSummaryState
    data class Error(val message: String) : ArticleSummaryState
}
