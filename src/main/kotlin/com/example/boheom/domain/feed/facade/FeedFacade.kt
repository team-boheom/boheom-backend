package com.example.boheom.domain.feed.facade

import com.example.boheom.domain.feed.domain.Feed
import com.example.boheom.domain.feed.domain.repository.ApplyRepository
import com.example.boheom.domain.feed.domain.repository.FeedRepository
import com.example.boheom.domain.feed.domain.repository.FeedTagRepository
import com.example.boheom.domain.feed.exception.FeedNotFoundException
import com.example.boheom.domain.feed.presentation.dto.response.FeedElement
import com.example.boheom.domain.feed.presentation.dto.response.FeedListResponse
import com.example.boheom.domain.user.domain.User
import org.springframework.stereotype.Component
import java.util.*

@Component
class FeedFacade(
    private val feedRepository: FeedRepository,
    private val feedTagRepository: FeedTagRepository,
    private val applyRepository: ApplyRepository,
) {
    fun getByFeedId(feedId: UUID): Feed {
        return feedRepository.findById(feedId).orElseThrow { FeedNotFoundException }
    }

    fun getFeedList(feeds: List<Feed>, user: User): List<FeedElement> {
        return feeds.map { feed ->
            val tags = feedTagRepository.findAllByFeed(feed)
            val applyCount = applyRepository.countByFeed(feed)
            val isApplied = applyRepository.existsByUserAndFeed(user, feed)

            FeedElement(feed.id, feed.title, feed.content, feed.view, tags.map { it.name }, feed.recruitment, applyCount, isApplied)
        }
    }
}