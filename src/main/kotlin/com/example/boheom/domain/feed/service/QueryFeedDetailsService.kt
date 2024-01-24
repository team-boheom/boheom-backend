package com.example.boheom.domain.feed.service

import com.example.boheom.domain.feed.domain.Feed
import com.example.boheom.domain.feed.domain.repository.ApplyRepository
import com.example.boheom.domain.feed.domain.repository.FeedTagRepository
import com.example.boheom.domain.feed.facade.FeedFacade
import com.example.boheom.domain.feed.presentation.dto.response.FeedDetailsResponse
import com.example.boheom.domain.user.domain.User
import com.example.boheom.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class QueryFeedDetailsService(
    val userFacade: UserFacade,
    val feedFacade: FeedFacade,
    val feedTagRepository: FeedTagRepository,
    val applyRepository: ApplyRepository,
) {
    @Transactional
    fun execute(feedId: UUID): FeedDetailsResponse {
        val user = userFacade.getCurrentUser()
        val feed = feedFacade.getByFeedId(feedId)
        val tags = feedTagRepository.findAllByFeed(feed).map { it.name }
        val applyCount = applyRepository.countByFeed(feed)
        val isApplied = applyRepository.existsByUserAndFeed(user, feed)

        feed.plusView()

        return FeedDetailsResponse(
            feed.id,
            feed.title,
            feed.content,
            feed.user.nickname,
            feed.startDay,
            feed.endDay,
            feed.createdAt,
            tags,
            feed.view,
            feed.recruitment,
            applyCount,
            isApplied,
            getIsMine(feed, user)
        )
    }

    fun getIsMine(feed: Feed, user: User): Boolean {
        return user.equals(feed.user)
    }
}