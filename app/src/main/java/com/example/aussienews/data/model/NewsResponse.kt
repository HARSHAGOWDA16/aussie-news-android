package com.example.aussienews.data.model

data class NewsResponse(
    val articles: List<ArticleDto>
)

data class ArticleDto(
    val title: String,
    val description: String?,
    val image: String?
)
