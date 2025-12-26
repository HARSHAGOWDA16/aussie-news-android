package com.example.aussienews.data.repository

import com.example.aussienews.data.model.Article
import com.example.aussienews.data.remote.ApiConfig
import com.example.aussienews.data.remote.NewsApi

class NewsRepository(
    private val api: NewsApi
) {

    suspend fun getTopHeadlines(
        category: String,
        apiKey: String
    ): List<Article> {
        val response = api.getTopHeadlines(
            category = category,
            apiKey = apiKey
        )
        return response.articles.map {
            Article(
                title = it.title,
                description = it.description,
                imageUrl = it.image
            )
        }
    }
}
