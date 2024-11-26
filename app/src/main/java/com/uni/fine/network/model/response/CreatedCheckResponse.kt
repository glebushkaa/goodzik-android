package com.uni.fine.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreatedCheckResponse(
    @SerialName("id")
    val id: String,
    @SerialName("prompt")
    val prompt: String,
    @SerialName("title")
    val title: String,
    @SerialName("summary")
    val summary: String,
    @SerialName("issues")
    val issues: List<Issue>,
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("aiScore")
    val aiScore: Double,
) {
    @Serializable
    data class Issue(
        @SerialName("id")
        val id: String,
        @SerialName("type")
        val type: IssueType,
        @SerialName("text")
        val text: String,
        @SerialName("message")
        val message: String,
        @SerialName("suggestion")
        val suggestion: String,
        @SerialName("startIndex")
        val start: Int,
        @SerialName("endIndex")
        val end: Int,
    ) {
        @Serializable
        enum class IssueType {
            @SerialName("grammar")
            Grammar,
            @SerialName("style")
            Style,
            @SerialName("vocabulary")
            Vocabulary,
            @SerialName("tone")
            Tone
        }
    }
}