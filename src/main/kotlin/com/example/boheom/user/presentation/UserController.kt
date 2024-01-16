package com.example.boheom.user.presentation

import com.example.boheom.user.presentation.dto.request.SignInRequest
import com.example.boheom.user.presentation.dto.request.SignUpRequest
import com.example.boheom.user.presentation.dto.response.TokenResponse
import com.example.boheom.user.service.SignInService
import com.example.boheom.user.service.SignUpService
import org.springframework.http.HttpStatus.*
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RequestMapping("/users")
@RestController
class UserController(
    private val signUpService: SignUpService,
    private val signInService: SignInService,
) {
    @ResponseStatus(CREATED)
    @PostMapping("/signup")
    fun signup(@RequestBody @Valid request: SignUpRequest) {
        signUpService.execute(request)
    }

    @PostMapping("/login")
    fun login(@RequestBody @Valid request: SignInRequest): TokenResponse {
        return signInService.execute(request)
    }
}