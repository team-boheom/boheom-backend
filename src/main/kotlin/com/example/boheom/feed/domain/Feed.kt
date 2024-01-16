package com.example.boheom.feed.domain

import com.example.boheom.global.entity.BaseUUIDEntity
import com.example.boheom.user.domain.User
import java.time.LocalDate
import java.util.Date
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType.*
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity(name = "tbl_feed")
class Feed(
    @Column(columnDefinition = "VARCHAR(50)", nullable = false)
    var title: String,

    @Column(columnDefinition = "VARCHAR(500)", nullable = false)
    var content: String,

    @Column(columnDefinition = "VARCHAR(15)", nullable = false)
    var tag: String,

    @Column(columnDefinition = "DATE", nullable = false)
    var startDay: LocalDate,

    @Column(columnDefinition = "DATE", nullable = false)
    var endDay: LocalDate,

    @Column(columnDefinition = "INT(2)", nullable = false)
    var recruitment: Int,

    @Column(columnDefinition = "INT(100)", nullable = false)
    var view: Int,

    user: User
): BaseUUIDEntity() {
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "BINARY(16)", nullable = false)
    var user = user
        protected  set
}