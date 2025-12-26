package com.example.aussienews.data.model

data class AiSummaryRequest(
    val model: String = "gpt-3.5-turbo",
    val messages: List<Message>,
    val temperature: Double = 0.3
)

data class Message(
    val role: String,
    val content: String
)

data class AiSummaryResponse(
    val choices: List<Choice>
)

data class Choice(
    val message: Message
)
