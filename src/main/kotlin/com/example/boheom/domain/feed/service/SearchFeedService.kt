package com.example.boheom.domain.feed.service

import com.example.boheom.domain.feed.domain.repository.FeedRepository
import com.example.boheom.domain.feed.facade.FeedFacade
import com.example.boheom.domain.feed.presentation.dto.response.PageFeedListResponse
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SearchFeedService(
    private val feedFacade: FeedFacade,
    private val feedRepository: FeedRepository
) {
    @Transactional(readOnly = true)
    fun execute(keyword: String, pageable: Pageable): PageFeedListResponse {
        val feeds = feedRepository.findAllByTitleContainingOrderByCreatedAtAsc(keyword, pageable)
        return PageFeedListResponse(
            feeds.totalPages,
            feeds.totalPages.equals(pageable.pageNumber + 1),
            feedFacade.getFeedList(feeds.content)
        )
    }
}