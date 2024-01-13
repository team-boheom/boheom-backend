package com.example.boheom.user.presentation.dto.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class SignUpRequest(
    @field:NotBlank(message = "null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @field:Size(max = 20, message = "최대 20자까지 가능합니다.")
    val accountId: String,

    @field:NotBlank(message = "null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @field:Size(max = 15, message = "최대 15자까지 가능합니다.")
    val nickname: String,

    @field:NotBlank
    @field:Pattern(
        regexp = "(?=.*[a-z])(?=.*[0-9])(?=.*[!#$%&'()*+,./:;<=>?@＼^_`{|}~])[a-zA-Z0-9!#$%&'()*+,./:;" +
                "<=>?@＼^_`{|}~]{8,30}$",
        message = "password는 소문자, 숫자, 특수문자가 포함되어야 합니다."
    )
    val password: String,
    val checkPassword: String
)
