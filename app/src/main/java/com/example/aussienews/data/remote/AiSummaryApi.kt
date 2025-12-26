package com.example.aussienews.data.remote

import com.example.aussienews.data.model.AiSummaryRequest
import com.example.aussienews.data.model.AiSummaryResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AiSummaryApi {

    @Headers(
        "Content-Type: application/json",
        "Authorization: Bearer ${AiConfig.OPENAI_API_KEY}"
    )
    @POST("v1/chat/completions")
    suspend fun getSummary(
        @Body request: AiSummaryRequest
    ): AiSummaryResponse
}
