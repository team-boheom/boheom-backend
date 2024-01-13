package com.example.boheom.user.presentation.dto.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class SignInRequest(
    @field:NotBlank(message = "null, 공백, 띄어쓰기를 허용하지 않습니다.")
    val accountId: String,

    @field:NotBlank(message = "null, 공백, 띄어쓰기를 허용하지 않습니다.")
    val password: String,
)
