package com.example.boheom.domain.feed.domain

import com.example.boheom.global.entity.BaseUUIDEntity
import com.example.boheom.domain.user.domain.User
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType.LAZY
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity(name = "tbl_feed")
class Feed(
    @Column(columnDefinition = "VARCHAR(50)", nullable = false)
    var title: String,

    @Column(columnDefinition = "VARCHAR(500)", nullable = false)
    var content: String,

    @Column(columnDefinition = "DATE", nullable = false)
    var startDay: LocalDate,

    @Column(columnDefinition = "DATE", nullable = false)
    var endDay: LocalDate,

    @Column(columnDefinition = "INT(2)", nullable = false)
    var recruitment: Int,

    @Column(columnDefinition = "INT(100)", nullable = false)
    var view: Int,

    @CreatedDate
    @Column(columnDefinition = "DATETIME", nullable = false)
    val createdAt: LocalDateTime,

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "BINARY(16)", nullable = false)
    val user: User
) : BaseUUIDEntity() {
    fun updateFeed(title: String, content: String, startDay: LocalDate, endDay: LocalDate, recruitment: Int) {
        this.title = title
        this.content = content
        this.startDay = startDay
        this.endDay = endDay
        this.recruitment = recruitment
    }
    fun plusView() {
        this.view += 1
    }
}