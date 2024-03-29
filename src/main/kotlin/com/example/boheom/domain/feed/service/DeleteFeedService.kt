package com.example.boheom.domain.feed.service

import com.example.boheom.domain.feed.domain.repository.ApplyRepository
import com.example.boheom.domain.feed.domain.repository.FeedRepository
import com.example.boheom.domain.feed.domain.repository.FeedTagRepository
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
    private val feedRepository: FeedRepository,
    private val feedTagRepository: FeedTagRepository,
    private val applyRepository: ApplyRepository,
) {
    @Transactional
    fun execute(feedId: UUID) {
        val feed = feedFacade.getByFeedId(feedId)
        val user = userFacade.getCurrentUser()

        if (user != feed.user) {
            throw IncorrectUserException
        }

        if (applyRepository.existsByFeed(feed)) {
            applyRepository.deleteByFeed(feed)
        }

        feedTagRepository.deleteAllByFeed(feed)
        feedRepository.delete(feed)
    }
}