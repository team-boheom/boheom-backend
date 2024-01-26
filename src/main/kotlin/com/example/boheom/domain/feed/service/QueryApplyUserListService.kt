package com.example.boheom.domain.feed.service

import com.example.boheom.domain.feed.domain.repository.ApplyRepository
import com.example.boheom.domain.feed.exception.IncorrectUserException
import com.example.boheom.domain.feed.facade.FeedFacade
import com.example.boheom.domain.feed.presentation.dto.response.QueryApplyUserListResponse
import com.example.boheom.domain.user.presentation.dto.response.UserElement
import com.example.boheom.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class QueryApplyUserListService(
    private val userFacade: UserFacade,
    private val feedFacade: FeedFacade,
    private val applyRepository: ApplyRepository,
) {
    @Transactional(readOnly = true)
    fun execute(feedId: UUID): QueryApplyUserListResponse {
        val user = userFacade.getCurrentUser()
        val feed = feedFacade.getByFeedId(feedId)
        val applyUser = applyRepository.findByFeed(feed).map { it.user }
        return QueryApplyUserListResponse(applyUser.map { UserElement(it.profile, it.nickname, it.accountId) })
    }
}