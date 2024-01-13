package com.example.boheom.global.error

import com.example.boheom.global.error.exception.BoheomException
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.valueOf
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(BoheomException::class)
    fun customExceptionHandling(e: BoheomException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(ErrorResponse.of(e), valueOf(e.status))
    }

    @ExceptionHandler(BindException::class)
    fun handleBindException(e: BindException): ResponseEntity<*>? {
        val errorMap: MutableMap<String, String?> = HashMap()
        for (error in e.fieldErrors) {
            errorMap[error.field] = error.defaultMessage
        }
        return ResponseEntity<Map<String, String?>>(errorMap, BAD_REQUEST)
    }
}