package com.example.boheom.domain.feed.exception

import com.example.boheom.global.error.exception.BoheomException
import com.example.boheom.global.error.exception.ErrorCode.ALREADY_APPLY

object AlreadyApplyException : BoheomException(ALREADY_APPLY)