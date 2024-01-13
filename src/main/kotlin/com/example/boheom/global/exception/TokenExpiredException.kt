package com.example.boheom.global.exception

import com.example.boheom.global.error.exception.BoheomException
import com.example.boheom.global.error.exception.ErrorCode.TOKEN_EXPIRED

object TokenExpiredException : BoheomException(TOKEN_EXPIRED) {
    val EXCEPTION = TokenExpiredException
}