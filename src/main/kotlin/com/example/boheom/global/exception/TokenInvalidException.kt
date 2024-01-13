package com.example.boheom.global.exception

import com.example.boheom.global.error.exception.BoheomException
import com.example.boheom.global.error.exception.ErrorCode.TOKEN_INVALID

object TokenInvalidException : BoheomException(TOKEN_INVALID) {
    val EXCEPTION = TokenInvalidException
}