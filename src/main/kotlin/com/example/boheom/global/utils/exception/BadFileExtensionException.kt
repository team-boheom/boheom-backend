package com.example.boheom.global.utils.exception

import com.example.boheom.global.error.exception.BoheomException
import com.example.boheom.global.error.exception.ErrorCode.BAD_FILE_EXTENSION

object BadFileExtensionException : BoheomException(BAD_FILE_EXTENSION)