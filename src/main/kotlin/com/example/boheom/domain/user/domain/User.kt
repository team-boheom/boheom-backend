package com.example.boheom.domain.user.domain

import com.example.boheom.global.entity.BaseUUIDEntity
import javax.persistence.Column
import javax.persistence.Entity

@Entity(name = "tbl_user")
class User(
    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    var accountId: String,

    @Column(columnDefinition = "CHAR(60)", nullable = false)
    var password: String,

    @Column(columnDefinition = "VARCHAR(15)", nullable = false)
    var nickname: String,

    @Column(columnDefinition = "VARCHAR(3000)", nullable = false)
    var profile: String,
) : BaseUUIDEntity() {
    fun updateProfile(profile: String) {
        this.profile = profile
    }
}