package com.example.boheom.domain.feed.domain

import com.example.boheom.global.entity.BaseUUIDEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType.LAZY
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity(name = "tbl_feed_tags")
class FeedTag(
    @Column(columnDefinition = "VARCHAR(10) default ''", nullable = false)
    var tag: String,

    @ManyToOne(fetch = LAZY)
    @JoinColumn(columnDefinition = "BINARY(16)", name = "feed_id", nullable = false)
    val feed: Feed
) : BaseUUIDEntity() {
    fun updateFeedTag(tag: String) {
        this.tag = tag
    }
}