package com.example.boheom.user.service

import com.example.boheom.user.domain.User
import com.example.boheom.user.domain.repository.UserRepository
import com.example.boheom.user.exception.AlreadyAccountIdException
import com.example.boheom.user.exception.AlreadyNicknameException
import com.example.boheom.user.exception.UnmatchedPasswordException
import com.example.boheom.user.facade.UserFacade
import com.example.boheom.user.presentation.dto.request.SignUpRequest
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
        checkExistUser(request)
        userRepository.save(User(request.accountId, passwordEncoder.encode(request.password), request.nickname, ""))
    }

    private fun checkExistUser(request: SignUpRequest) {
        if (userFacade.checkAccountIdExist(request.accountId)) {
            throw AlreadyAccountIdException
        }
        if (userFacade.checkNicknameExist(request.nickname)) {
            throw AlreadyNicknameException
        }
        if (request.password == request.checkPassword) {
            throw UnmatchedPasswordException
        }
    }
}