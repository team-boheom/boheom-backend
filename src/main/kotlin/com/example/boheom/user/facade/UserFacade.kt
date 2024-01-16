package com.example.boheom.user.facade

import com.example.boheom.user.domain.User
import com.example.boheom.user.domain.repository.UserRepository
import com.example.boheom.user.exception.UserNotFoundException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.*

@Component
class UserFacade(
    private val userRepository: UserRepository
) {
    fun getCurrentUser(): User {
        val accountId: String = SecurityContextHolder.getContext().authentication.name
        return getByAccountId(accountId)
    }

    fun checkAccountIdExist(accountId: String): Boolean {
        return userRepository.existsByAccountId(accountId)
    }

    fun checkNicknameExist(nickname: String): Boolean {
        return userRepository.existsByNickname(nickname)
    }

    fun getByAccountId(accountId: String): User {
        return userRepository.findByAccountId(accountId) ?: throw UserNotFoundException
    }
}