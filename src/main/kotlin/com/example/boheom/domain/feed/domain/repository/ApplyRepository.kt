package com.example.boheom.domain.feed.domain.repository

import com.example.boheom.domain.feed.domain.Apply
import com.example.boheom.domain.feed.domain.Feed
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ApplyRepository : JpaRepository<Apply, UUID>