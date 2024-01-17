package com.example.boheom.domain.feed.service

import com.example.boheom.domain.feed.domain.repository.ApplyRepository
import com.example.boheom.domain.feed.domain.repository.FeedRepository
import com.example.boheom.domain.feed.domain.repository.FeedTagRepository
import com.example.boheom.domain.feed.presentation.dto.response.FeedElement
import com.example.boheom.domain.feed.presentation.dto.response.FeedListResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PopularFeedListService(
    private val feedRepository: FeedRepository,
    private val feedTagRepository: FeedTagRepository,
    private val applyRepository: ApplyRepository,
) {
    @Transactional(readOnly = true)
    fun execute(): FeedListResponse {
        val feeds = feedRepository.findAllByOrderByViewDesc()
        val feedElements = feeds.map { feed ->
            val tags = feedTagRepository.findAllByFeed(feed)
            val applyCount = applyRepository.countByFeed(feed)
            FeedElement(feed.id, feed.title, feed.content, feed.view, tags.map { it.name }, feed.recruitment, applyCount)
        }

        return FeedListResponse(feedElements)
    }
}