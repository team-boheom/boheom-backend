package com.example.boheom.domain.feed.domain.repository

import com.example.boheom.domain.feed.domain.Feed
import com.example.boheom.domain.user.domain.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface FeedRepository : JpaRepository<Feed, UUID> {
    fun findAllByOrderByCreatedAtAsc(): List<Feed>
    fun findAllByOrderByViewDesc(): List<Feed>
    fun findAllByTitleContainingOrderByCreatedAtAsc(title: String, pageable: Pageable): Page<Feed>
    fun findAllByUserOrderByCreatedAtAsc(user: User): List<Feed>
}