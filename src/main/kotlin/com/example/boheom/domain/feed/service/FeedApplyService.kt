package com.example.boheom.domain.feed.service

import com.example.boheom.domain.feed.domain.Apply
import com.example.boheom.domain.feed.domain.Feed
import com.example.boheom.domain.feed.domain.repository.ApplyRepository
import com.example.boheom.domain.feed.exception.AlreadyApplyException
import com.example.boheom.domain.feed.exception.ImpossibleApplicationException
import com.example.boheom.domain.feed.exception.NotAllowSelfApplicationException
import com.example.boheom.domain.feed.facade.FeedFacade
import com.example.boheom.domain.user.domain.User
import com.example.boheom.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class FeedApplyService(
    private val userFacade: UserFacade,
    private val feedFacade: FeedFacade,
    private val applyRepository: ApplyRepository,
) {
    @Transactional
    fun execute(feedId: UUID) {
        val user = userFacade.getCurrentUser()
        val feed = feedFacade.getByFeedId(feedId)
        validateApply(feed, user)
        applyRepository.save(Apply(user, feed))
    }
    fun validateApply(feed: Feed, user: User) {
        val applyCount = applyRepository.countByFeed(feed)

        if (user.equals(feed.user)) {
            throw NotAllowSelfApplicationException
        }
        if (applyRepository.existsByUserAndFeed(user, feed)) {
            throw AlreadyApplyException
        }
        if (feed.recruitment.equals(applyCount)) {
            throw ImpossibleApplicationException
        }
    }
}