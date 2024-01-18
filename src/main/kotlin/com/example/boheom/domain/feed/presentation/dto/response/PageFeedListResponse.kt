package com.example.boheom.domain.feed.presentation.dto.response

data class PageFeedListResponse(
    val totalPage: Int,
    val feeds: List<FeedElement>
)
