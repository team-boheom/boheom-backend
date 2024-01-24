package com.example.boheom.domain.feed.service

import com.example.boheom.domain.feed.domain.repository.ApplyRepository
import com.example.boheom.domain.feed.facade.FeedFacade
import com.example.boheom.domain.feed.presentation.dto.response.FeedListResponse
import com.example.boheom.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryApplyFeedService(
    private val userFacade: UserFacade,
    private val feedFacade: FeedFacade,
    private val applyRepository: ApplyRepository,
) {
    @Transactional(readOnly = true)
    fun execute(): FeedListResponse {
        val user = userFacade.getCurrentUser()
        val feeds = applyRepository.findByUser(user).map { it.feed }
        return FeedListResponse(feedFacade.getFeedList(feeds, user))
    }
}