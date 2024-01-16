package com.example.boheom.domain.feed.service

import com.example.boheom.domain.feed.domain.repository.FeedRepository
import com.example.boheom.domain.feed.exception.IncorrectUserException
import com.example.boheom.domain.feed.facade.FeedFacade
import com.example.boheom.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class DeleteFeedService(
    private val feedFacade: FeedFacade,
    private val userFacade: UserFacade,
    private val feedRepository: FeedRepository
) {
    @Transactional
    fun execute(feedId: UUID) {
        val feed = feedFacade.getByFeedId(feedId)
        val user = userFacade.getCurrentUser()

        if (user != feed.user) {
            throw IncorrectUserException
        }

        feedRepository.delete(feed)
    }
}