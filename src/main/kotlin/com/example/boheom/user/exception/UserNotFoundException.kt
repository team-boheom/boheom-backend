package com.example.boheom.user.exception

import com.example.boheom.global.error.exception.BoheomException
import com.example.boheom.global.error.exception.ErrorCode.*

object UserNotFoundException : BoheomException(USER_NOT_FOUND)