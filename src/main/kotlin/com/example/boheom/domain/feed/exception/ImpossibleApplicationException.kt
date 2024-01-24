package com.example.boheom.domain.feed.exception

import com.example.boheom.global.error.exception.BoheomException
import com.example.boheom.global.error.exception.ErrorCode.IMPOSSIBLE_APPLICATION

object ImpossibleApplicationException: BoheomException(IMPOSSIBLE_APPLICATION)