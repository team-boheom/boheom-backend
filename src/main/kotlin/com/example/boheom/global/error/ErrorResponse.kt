package com.example.boheom.global.error

import com.example.boheom.global.error.exception.BoheomException

class ErrorResponse(
    val status: Int,
    val message: String,
) {
    companion object {
        fun of(e: BoheomException): ErrorResponse {
            return ErrorResponse(e.status, e.message)
        }
    }
}