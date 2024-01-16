package com.example.boheom.domain.user.service

import com.example.boheom.domain.user.domain.User
import com.example.boheom.domain.user.domain.repository.UserRepository
import com.example.boheom.domain.user.exception.AlreadyAccountIdException
import com.example.boheom.domain.user.exception.AlreadyNicknameException
import com.example.boheom.domain.user.exception.UnmatchedPasswordException
import com.example.boheom.domain.user.facade.UserFacade
import com.example.boheom.domain.user.presentation.dto.request.SignUpRequest
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SignUpService(
    private val userRepository: UserRepository,
    private val userFacade: UserFacade,
    private val passwordEncoder: PasswordEncoder
) {
    @Transactional
    fun execute(request: SignUpRequest) {
        checkUser(request)
        userRepository.save(User(request.accountId, passwordEncoder.encode(request.password), request.nickname, ""))
    }

    private fun checkUser(request: SignUpRequest) {
        if (userFacade.checkAccountIdExist(request.accountId)) {
            throw AlreadyAccountIdException
        }
        if (userFacade.checkNicknameExist(request.nickname)) {
            throw AlreadyNicknameException
        }
        if (!request.password.equals(request.checkPassword)) {
            throw UnmatchedPasswordException
        }
    }
}