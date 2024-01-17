package com.example.boheom.domain.feed.service

import com.example.boheom.domain.feed.domain.repository.ApplyRepository
import com.example.boheom.domain.feed.domain.repository.FeedRepository
import com.example.boheom.domain.feed.domain.repository.FeedTagRepository
import com.example.boheom.domain.feed.presentation.dto.response.FeedElement
import com.example.boheom.domain.feed.presentation.dto.response.FeedListResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RecentFeedService(
    private val feedRepository: FeedRepository,
    private val feedTagRepository: FeedTagRepository,
    private val applyRepository: ApplyRepository,
) {
    @Transactional(readOnly = true)
    fun execute(): FeedListResponse {
        val feeds = feedRepository.findAllByOrderByCreatedAtAsc()
        val feedElements = feeds.map {
            val tags = feedTagRepository.findAllByFeed(it)
            val applyCount = applyRepository.countByFeed(it)

            FeedElement(
                it.id,
                it.title,
                it.content,
                it.view,
                tags.map { it.tag },
                it.recruitment,
                applyCount
            )
        }

        return FeedListResponse(feedElements)
    }
}