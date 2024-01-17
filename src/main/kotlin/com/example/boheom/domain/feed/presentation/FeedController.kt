package com.example.boheom.domain.feed.presentation

import com.example.boheom.domain.feed.presentation.dto.request.CreateFeedRequest
import com.example.boheom.domain.feed.presentation.dto.request.UpdateFeedRequest
import com.example.boheom.domain.feed.presentation.dto.response.FeedListResponse
import com.example.boheom.domain.feed.service.CategoryFeedService
import com.example.boheom.domain.feed.service.CreateFeedService
import com.example.boheom.domain.feed.service.DeleteFeedService
import com.example.boheom.domain.feed.service.FeedApplyService
import com.example.boheom.domain.feed.service.PopularFeedListService
import com.example.boheom.domain.feed.service.RecentFeedService
import com.example.boheom.domain.feed.service.UpdateFeedService
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import javax.validation.Valid

@RequestMapping("/feeds")
@RestController
class FeedController(
    private val createFeedService: CreateFeedService,
    private val updateFeedService: UpdateFeedService,
    private val deleteFeedService: DeleteFeedService,
    private val feedApplyService: FeedApplyService,
    private val recentFeedService: RecentFeedService,
    private val popularFeedListService: PopularFeedListService,
    private val categoryFeedService: CategoryFeedService,
) {
    @ResponseStatus(CREATED)
    @PostMapping
    fun createFeed(@RequestBody @Valid request: CreateFeedRequest) {
        createFeedService.execute(request)
    }

    @ResponseStatus(NO_CONTENT)
    @PatchMapping("/{feed-id}")
    fun updateFeed(@PathVariable("feed-id") feedId: UUID, @RequestBody @Valid request: UpdateFeedRequest) {
        updateFeedService.execute(feedId, request)
    }

    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("/{feed-id}")
    fun deleteFeed(@PathVariable("feed-id") feedId: UUID) {
        deleteFeedService.execute(feedId)
    }

    @ResponseStatus(CREATED)
    @PostMapping("/{feed-id}")
    fun feedApply(@PathVariable("feed-id") feedId: UUID) {
        feedApplyService.execute(feedId)
    }

    @GetMapping("/recent")
    fun getRecentFeed(): FeedListResponse {
        return recentFeedService.execute()
    }

    @GetMapping("/popular")
    fun getPopularFeed(): FeedListResponse {
        return popularFeedListService.execute()
    }

    @GetMapping("/{category}")
    fun getCategoryFeed(@PathVariable("category") category: String): FeedListResponse {
        return categoryFeedService.execute(category)
    }
}