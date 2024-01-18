package com.example.boheom.domain.feed.presentation

import com.example.boheom.domain.feed.presentation.dto.request.CreateFeedRequest
import com.example.boheom.domain.feed.presentation.dto.request.UpdateFeedRequest
import com.example.boheom.domain.feed.presentation.dto.response.FeedDetailsResponse
import com.example.boheom.domain.feed.presentation.dto.response.FeedListResponse
import com.example.boheom.domain.feed.presentation.dto.response.PageFeedListResponse
import com.example.boheom.domain.feed.service.CreateFeedService
import com.example.boheom.domain.feed.service.DeleteFeedService
import com.example.boheom.domain.feed.service.FeedApplyService
import com.example.boheom.domain.feed.service.QueryApplyFeedService
import com.example.boheom.domain.feed.service.QueryCategoryFeedService
import com.example.boheom.domain.feed.service.QueryFeedDetailsService
import com.example.boheom.domain.feed.service.QueryMyFeedService
import com.example.boheom.domain.feed.service.QueryPopularFeedListService
import com.example.boheom.domain.feed.service.QueryRecentFeedService
import com.example.boheom.domain.feed.service.SearchFeedService
import com.example.boheom.domain.feed.service.CancelApplyService
import com.example.boheom.domain.feed.service.UpdateFeedService
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
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
    private val cancelApplyService: CancelApplyService,
    private val queryRecentFeedService: QueryRecentFeedService,
    private val queryPopularFeedListService: QueryPopularFeedListService,
    private val queryCategoryFeedService: QueryCategoryFeedService,
    private val queryFeedDetailsService: QueryFeedDetailsService,
    private val searchFeedService: SearchFeedService,
    private val queryApplyFeedService: QueryApplyFeedService,
    private val queryMyFeedService: QueryMyFeedService,
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

    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("/cancel/{feed-id}")
    fun cancelApply(@PathVariable("feed-id") feedId: UUID) {
        cancelApplyService.execute(feedId)
    }

    @GetMapping("/recent")
    fun getRecentFeed(): FeedListResponse {
        return queryRecentFeedService.execute()
    }

    @GetMapping("/popular")
    fun getPopularFeed(): FeedListResponse {
        return queryPopularFeedListService.execute()
    }

    @GetMapping("/{category}")
    fun getCategoryFeed(@PathVariable("category") category: String): FeedListResponse {
        return queryCategoryFeedService.execute(category)
    }

    @GetMapping("/details/{feed-id}")
    fun getFeedDetails(@PathVariable("feed-id") feedId: UUID): FeedDetailsResponse {
        return queryFeedDetailsService.execute(feedId)
    }

    @GetMapping("/search/{keyword}")
    fun searchFeed(@PathVariable("keyword") keyword: String,@PageableDefault(size = 12) pageable: Pageable): PageFeedListResponse {
        return searchFeedService.execute(keyword, pageable)
    }

    @GetMapping("/applied")
    fun getApplyFeed(): FeedListResponse {
        return queryApplyFeedService.execute()
    }

    @GetMapping("/mine")
    fun getMyFeed(): FeedListResponse {
        return queryMyFeedService.execute()
    }
}