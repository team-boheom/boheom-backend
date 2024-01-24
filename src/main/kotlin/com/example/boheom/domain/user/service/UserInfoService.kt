package com.example.boheom.domain.user.service

import com.example.boheom.domain.user.facade.UserFacade
import com.example.boheom.domain.user.presentation.dto.response.UserElement
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserInfoService(
    private val userFacade: UserFacade,
) {
    @Transactional(readOnly = true)
    fun execute(): UserElement {
        val user = userFacade.getCurrentUser()
        return UserElement(user.profile, user.nickname, user.accountId)
    }
}