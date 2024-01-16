package com.example.boheom.feed.exception

import com.example.boheom.global.error.exception.BoheomException
import com.example.boheom.global.error.exception.ErrorCode.INCORRECT_USER

object IncorrectUserException : BoheomException(INCORRECT_USER)