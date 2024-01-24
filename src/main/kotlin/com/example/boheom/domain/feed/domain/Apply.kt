package com.example.boheom.domain.feed.domain

import com.example.boheom.domain.user.domain.User
import com.example.boheom.global.entity.BaseUUIDEntity
import javax.persistence.Entity
import javax.persistence.FetchType.LAZY
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity(name = "tbl_apply")
class Apply(
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "BINARY(16)", nullable = false)
    val user: User,

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "feed_id", columnDefinition = "BINARY(16)", nullable = false)
    val feed: Feed
) : BaseUUIDEntity()