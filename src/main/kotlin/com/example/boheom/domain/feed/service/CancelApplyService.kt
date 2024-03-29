package com.example.boheom.domain.feed.service

import com.example.boheom.domain.feed.domain.Apply
import com.example.boheom.domain.feed.domain.repository.ApplyRepository
import com.example.boheom.domain.feed.facade.FeedFacade
import com.example.boheom.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class CancelApplyService(
    private val userFacade: UserFacade,
    private val feedFacade: FeedFacade,
    private val applyRepository: ApplyRepository
) {
    @Transactional
    fun execute(feedId: UUID) {
        val user = userFacade.getCurrentUser()
        val feed = feedFacade.getByFeedId(feedId)
        applyRepository.deleteByUserAndFeed(user, feed)
    }
}