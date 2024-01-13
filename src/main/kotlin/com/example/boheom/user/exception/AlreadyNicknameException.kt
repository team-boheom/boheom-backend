package com.example.boheom.user.exception

import com.example.boheom.global.error.exception.BoheomException
import com.example.boheom.global.error.exception.ErrorCode.*

object AlreadyNicknameException : BoheomException(ALREADY_NICKNAME) {
    val EXCEPTION = AlreadyNicknameException
}