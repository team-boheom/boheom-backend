package com.example.boheom.feed.facade

import com.example.boheom.feed.domain.Feed
import com.example.boheom.feed.domain.repository.FeedRepository
import com.example.boheom.feed.exception.FeedNotFoundException
import org.springframework.stereotype.Component
import java.util.*

@Component
class FeedFacade(
    private val feedRepository: FeedRepository,
) {
    fun getByFeedId(feedId: UUID): Feed {
        return feedRepository.findById(feedId).orElseThrow { FeedNotFoundException }
    }
}