package com.example.boheom.domain.feed.domain.repository

import com.example.boheom.domain.feed.domain.Feed
import com.example.boheom.domain.feed.domain.FeedTag
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface FeedTagRepository : JpaRepository<FeedTag, UUID> {
    fun deleteAllByFeed(feed: Feed)
    fun findAllByFeed(feed: Feed): List<FeedTag>
    fun findAllByNameContaining(name: String): List<FeedTag>
}