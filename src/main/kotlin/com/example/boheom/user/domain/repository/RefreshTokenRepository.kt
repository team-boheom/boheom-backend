package com.example.boheom.user.domain.repository

import com.example.boheom.user.domain.RefreshToken
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RefreshTokenRepository : CrudRepository<RefreshToken, String> {
    fun findByToken(refreshToken: String): RefreshToken
}