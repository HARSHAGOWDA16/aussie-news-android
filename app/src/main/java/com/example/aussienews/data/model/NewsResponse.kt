package com.example.aussienews.data.model

data class NewsResponse(
    val status: String,
    val articles: List<ArticleDto>
)

data class ArticleDto(
    val title: String,
    val description: String?,
    val urlToImage: String?
)