package com.example.boheom.domain.feed.service

import com.example.boheom.domain.feed.domain.repository.FeedRepository
import com.example.boheom.domain.feed.facade.FeedFacade
import com.example.boheom.domain.feed.presentation.dto.response.FeedListResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PopularFeedListService(
    private val feedRepository: FeedRepository,
    private val feedFacade: FeedFacade,
) {
    @Transactional(readOnly = true)
    fun execute(): FeedListResponse {
        val feeds = feedRepository.findAllByOrderByViewDesc()
        return feedFacade.getFeedList(feeds)
    }
}