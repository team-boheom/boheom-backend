package com.example.boheom.feed.domain

import com.example.boheom.global.entity.BaseUUIDEntity
import java.time.LocalDate
import java.util.Date
import javax.persistence.Column
import javax.persistence.Entity

@Entity(name = "tbl_feed")
class Feed(
    @Column(columnDefinition = "VARCHAR(50)", nullable = false)
    var title: String,

    @Column(columnDefinition = "VARCHAR(500)", nullable = false)
    var content: String,

    @Column(columnDefinition = "VARCHAR(15)", nullable = false)
    var tag: String,

    @Column(columnDefinition = "DATE(3)", nullable = false)
    var startDay: LocalDate,

    @Column(columnDefinition = "DATE(3)", nullable = false)
    var endDay: LocalDate,

    @Column(columnDefinition = "INT(3)", nullable = false)
    var recruitment: Int,

    @Column(columnDefinition = "INT(100)", nullable = false)
    var view: Int,
): BaseUUIDEntity()