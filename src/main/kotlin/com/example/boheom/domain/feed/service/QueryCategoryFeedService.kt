package com.example.boheom.domain.feed.service

import com.example.boheom.domain.feed.domain.repository.FeedTagRepository
import com.example.boheom.domain.feed.facade.FeedFacade
import com.example.boheom.domain.feed.presentation.dto.response.FeedListResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryCategoryFeedService(
    private val feedTagRepository: FeedTagRepository,
    private val feedFacade: FeedFacade,
) {
    @Transactional(readOnly = true)
    fun execute(keyword: String): FeedListResponse {
        val feedTags = feedTagRepository.findAllByNameContaining(keyword)
        return FeedListResponse(feedFacade.getFeedList(feedTags.map { it.feed }))
    }
}