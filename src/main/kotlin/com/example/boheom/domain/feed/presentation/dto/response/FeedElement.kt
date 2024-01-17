package com.example.boheom.domain.feed.presentation.dto.response

import java.util.UUID

data class FeedElement(
    val id: UUID,
    val title: String,
    val content: String,
    val view: Int,
    val tags: List<String>,
    val recruitment: Int,
    val applyCount: Int
)