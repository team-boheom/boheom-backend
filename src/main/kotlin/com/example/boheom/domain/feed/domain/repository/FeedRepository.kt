package com.example.boheom.domain.feed.domain.repository

import com.example.boheom.domain.feed.domain.Feed
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface FeedRepository : JpaRepository<Feed, UUID> {
    fun findAllByOrderByCreatedAtAsc(): List<Feed>
}