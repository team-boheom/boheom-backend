package com.example.boheom.global.exception

import com.example.boheom.global.error.exception.BoheomException
import com.example.boheom.global.error.exception.ErrorCode.*

object InternalServerError : BoheomException(INTERNAL_SERVER_ERROR) {
    val EXCEPTION = InternalServerError
}