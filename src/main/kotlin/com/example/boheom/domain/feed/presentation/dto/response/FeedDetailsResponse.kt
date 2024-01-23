package com.example.boheom.domain.feed.presentation.dto.response

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

data class FeedDetailsResponse(
    val id: UUID,
    val title: String,
    val content: String,
    val username: String,
    val startDay: LocalDate,
    val endDay: LocalDate,
    val createdAt: LocalDateTime,
    val tags: List<String>,
    val view: Int,
    val recruitment: Int,
    val applyCount: Int,
    val isMine: Boolean
)
