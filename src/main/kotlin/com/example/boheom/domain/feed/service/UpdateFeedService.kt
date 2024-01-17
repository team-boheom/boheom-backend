package com.example.boheom.domain.feed.service

import com.example.boheom.domain.feed.domain.FeedTag
import com.example.boheom.domain.feed.domain.repository.FeedTagRepository
import com.example.boheom.domain.feed.exception.IncorrectUserException
import com.example.boheom.domain.feed.facade.FeedFacade
import com.example.boheom.domain.feed.presentation.dto.request.UpdateFeedRequest
import com.example.boheom.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class UpdateFeedService(
    private val feedTagRepository: FeedTagRepository,
    private val feedFacade: FeedFacade,
    private val userFacade: UserFacade,
) {
    @Transactional
    fun execute(feedId: UUID, request: UpdateFeedRequest) {
        val feed = feedFacade.getByFeedId(feedId)
        val user = userFacade.getCurrentUser()
        if (user != feed.user) {
            throw IncorrectUserException
        }
        feed.updateFeed(request.title, request.content, request.startDay, request.endDay, request.recruitment)
        feedTagRepository.deleteAllByFeed(feed)
        request.tag.map { feedTagRepository.save(FeedTag(it, feed)) }
    }
}