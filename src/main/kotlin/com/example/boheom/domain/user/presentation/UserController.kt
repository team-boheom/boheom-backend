package com.example.boheom.domain.user.presentation

import com.example.boheom.domain.user.presentation.dto.request.SignInRequest
import com.example.boheom.domain.user.presentation.dto.request.SignUpRequest
import com.example.boheom.domain.user.presentation.dto.response.TokenResponse
import com.example.boheom.domain.user.presentation.dto.response.UploadProfileResponse
import com.example.boheom.domain.user.presentation.dto.response.UserInfoResponse
import com.example.boheom.domain.user.service.SignInService
import com.example.boheom.domain.user.service.SignUpService
import com.example.boheom.domain.user.service.UploadProfileService
import com.example.boheom.domain.user.service.UserInfoService
import org.springframework.http.HttpStatus.CREATED
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import javax.validation.Valid

@RequestMapping("/users")
@RestController
class UserController(
    private val signUpService: SignUpService,
    private val signInService: SignInService,
    private val uploadProfileService: UploadProfileService,
    private val userInfoService: UserInfoService,
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

    @PostMapping("/upload")
    fun uploadProfile(@RequestPart("file") file: MultipartFile): UploadProfileResponse {
        return uploadProfileService.execute(file)
    }

    @GetMapping
    fun getUserInfo(): UserInfoResponse {
        return userInfoService.execute()
    }
}