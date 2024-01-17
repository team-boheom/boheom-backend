package com.example.boheom.domain.feed.service

import com.example.boheom.domain.feed.domain.Feed
import com.example.boheom.domain.feed.domain.repository.FeedRepository
import com.example.boheom.domain.feed.domain.repository.FeedTagRepository
import com.example.boheom.domain.feed.facade.FeedFacade
import com.example.boheom.domain.feed.presentation.dto.response.FeedDetailsResponse
import com.example.boheom.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class FeedDetailsService(
    val userFacade: UserFacade,
    val feedFacade: FeedFacade,
    val feedTagRepository: FeedTagRepository
) {
    @Transactional
    fun execute(feedId: UUID): FeedDetailsResponse {
        val feed = feedFacade.getByFeedId(feedId)
        val tags = feedTagRepository.findAllByFeed(feed).map { it.name }
        return FeedDetailsResponse(feed.id, feed.title, feed.content, feed.user.nickname, feed.createdAt, tags, getIsMine(feed))
    }

    fun getIsMine(feed: Feed): Boolean {
        val user = userFacade.getCurrentUser()
        return user.equals(feed.user)
    }
}