package com.example.boheom.global.error

import com.example.boheom.global.error.exception.BoheomException
import com.example.boheom.global.exception.InternalServerError
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class ExceptionFilter(
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {
    @Throws
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: Exception) {
            e.printStackTrace()
            when (e) {
                is BoheomException -> writeErrorCode(e, response)
                else -> writeErrorCode(InternalServerError.EXCEPTION, response)
            }
        }
    }

    @Throws(IOException::class)
    private fun writeErrorCode(exception: BoheomException, response: HttpServletResponse) {
        val errorResponse = ErrorResponse.of(exception)

        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.status = errorResponse.status
        response.writer.write(objectMapper.writeValueAsString(errorResponse))
    }
}