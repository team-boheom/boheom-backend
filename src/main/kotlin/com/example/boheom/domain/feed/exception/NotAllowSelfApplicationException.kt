package com.example.boheom.domain.feed.exception

import com.example.boheom.global.error.exception.BoheomException
import com.example.boheom.global.error.exception.ErrorCode.NOT_ALLOW_SELF_APPLICATION

object NotAllowSelfApplicationException: BoheomException(NOT_ALLOW_SELF_APPLICATION)