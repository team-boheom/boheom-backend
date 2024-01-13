package com.example.boheom.global.error.exception

abstract class BoheomException(
    val errorCode: ErrorCode,
) : RuntimeException() {
    val status: Int
        get() = errorCode.status
    override val message: String
        get() = errorCode.message
}