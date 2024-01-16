package com.example.boheom.feed.domain.repository

import com.example.boheom.feed.domain.Feed
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface FeedRepository : JpaRepository<Feed, UUID> {
}