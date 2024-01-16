package com.example.boheom.domain.feed.service

import com.example.boheom.domain.feed.domain.Feed
import com.example.boheom.domain.feed.domain.repository.FeedRepository
import com.example.boheom.domain.feed.presentation.dto.request.CreateFeedRequest
import com.example.boheom.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CreateFeedService(
    private val feedRepository: FeedRepository,
    private val userFacade: UserFacade,
) {
    @Transactional
    fun execute(request: CreateFeedRequest) {
        val user = userFacade.getCurrentUser()
        feedRepository.save(
            Feed(
                request.title,
                request.content,
                request.tag,
                request.startDay,
                request.endDay,
                request.recruitment,
                0,
                user
            )
        )
    }
}