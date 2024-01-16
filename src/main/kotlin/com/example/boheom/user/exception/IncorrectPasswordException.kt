package com.example.boheom.user.exception

import com.example.boheom.global.error.exception.BoheomException
import com.example.boheom.global.error.exception.ErrorCode.*

object IncorrectPasswordException : BoheomException(INCORRECT_PASSWORD)