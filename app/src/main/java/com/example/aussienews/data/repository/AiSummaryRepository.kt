package com.example.aussienews.data.repository

import com.example.aussienews.data.model.*
import com.example.aussienews.data.remote.AiSummaryApi

class AiSummaryRepository(
    private val api: AiSummaryApi
) {

    suspend fun summarize(articleText: String): String {
        val request = AiSummaryRequest(
            messages = listOf(
                Message(
                    role = "user",
                    content = "Summarize the following news article in 4 bullet points:\n$articleText"
                )
            )
        )

        return api.getSummary(request)
            .choices
            .first()
            .message
            .content
    }
}
