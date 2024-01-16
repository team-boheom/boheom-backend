package com.example.boheom.feed.service

import com.example.boheom.feed.exception.IncorrectUserException
import com.example.boheom.feed.facade.FeedFacade
import com.example.boheom.feed.presentation.dto.request.UpdateFeedRequest
import com.example.boheom.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class UpdateFeedService(
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
        feed.updateFeed(request.title, request.content, request.tag, request.startDay, request.endDay, request.recruitment)
    }
}