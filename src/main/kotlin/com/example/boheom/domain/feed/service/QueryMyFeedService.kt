package com.example.boheom.domain.feed.service

import com.example.boheom.domain.feed.domain.repository.FeedRepository
import com.example.boheom.domain.feed.facade.FeedFacade
import com.example.boheom.domain.feed.presentation.dto.response.FeedListResponse
import com.example.boheom.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryMyFeedService(
    private val userFacade: UserFacade,
    private val feedFacade: FeedFacade,
    private val feedRepository: FeedRepository,
) {
    @Transactional(readOnly = true)
    fun execute(): FeedListResponse {
        val user = userFacade.getCurrentUser()
        val feeds = feedRepository.findAllByUserOrderByCreatedAtAsc(user)
        return FeedListResponse(feedFacade.getFeedList(feeds, user))
    }
}