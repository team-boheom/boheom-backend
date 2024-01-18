package com.example.boheom.domain.feed.presentation.dto.response

import java.time.LocalDateTime
import java.util.UUID

data class FeedDetailsResponse(
    val id: UUID,
    val title: String,
    val content: String,
    val username: String,
    val createdAt: LocalDateTime,
    val tags: List<String>,
    val isMine: Boolean
)