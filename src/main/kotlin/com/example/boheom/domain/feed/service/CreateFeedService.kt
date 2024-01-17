package com.example.boheom.domain.feed.service

import com.example.boheom.domain.feed.domain.Feed
import com.example.boheom.domain.feed.domain.FeedTag
import com.example.boheom.domain.feed.domain.repository.FeedRepository
import com.example.boheom.domain.feed.domain.repository.FeedTagRepository
import com.example.boheom.domain.feed.presentation.dto.request.CreateFeedRequest
import com.example.boheom.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CreateFeedService(
    private val feedRepository: FeedRepository,
    private val feedTagRepository: FeedTagRepository,
    private val userFacade: UserFacade,
) {
    @Transactional
    fun execute(request: CreateFeedRequest) {
        val user = userFacade.getCurrentUser()
        val feed = feedRepository.save(
            Feed(
                request.title,
                request.content,
                request.startDay,
                request.endDay,
                request.recruitment,
                0,
                user
            )
        )
        request.tag.map { feedTagRepository.save(FeedTag(it, feed)) }
    }
}