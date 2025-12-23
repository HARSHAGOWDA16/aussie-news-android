package com.example.aussienews.data.remote

import com.example.aussienews.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "au",
        @Query("apiKey") apiKey: String
    ): NewsResponse
}