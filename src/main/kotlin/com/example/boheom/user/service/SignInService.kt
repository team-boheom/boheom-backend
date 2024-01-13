package com.example.boheom.user.service

import com.example.boheom.global.security.jwt.TokenProvider
import com.example.boheom.user.exception.IncorrectPasswordException
import com.example.boheom.user.facade.UserFacade
import com.example.boheom.user.presentation.dto.request.SignInRequest
import com.example.boheom.user.presentation.dto.response.TokenResponse
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SignInService(
    private val userFacade: UserFacade,
    private val passwordEncoder: PasswordEncoder,
    private val tokenProvider: TokenProvider,
) {
    @Transactional
    fun execute(request: SignInRequest): TokenResponse {
        val user = userFacade.getByAccountId(request.accountId)
        if (passwordEncoder.matches(request.password, user.password)) {
            throw IncorrectPasswordException
        }
        return tokenProvider.generateToken(user.accountId)
    }
}