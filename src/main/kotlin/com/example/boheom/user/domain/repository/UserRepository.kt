package com.example.boheom.user.domain.repository

import com.example.boheom.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface UserRepository : JpaRepository<User, UUID> {
    fun findByAccountId(accountId: String): User?
    fun existsByAccountId(accountId: String): Boolean
    fun existsByNickname(nickname: String): Boolean
}