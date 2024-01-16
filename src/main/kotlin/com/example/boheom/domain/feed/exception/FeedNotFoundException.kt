package com.example.boheom.domain.feed.exception

import com.example.boheom.global.error.exception.BoheomException
import com.example.boheom.global.error.exception.ErrorCode.FEED_NOT_FOUND

object FeedNotFoundException : BoheomException(FEED_NOT_FOUND)