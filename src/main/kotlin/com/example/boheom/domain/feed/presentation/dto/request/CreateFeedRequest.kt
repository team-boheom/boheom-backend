package com.example.boheom.domain.feed.presentation.dto.request

import java.time.LocalDate
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class CreateFeedRequest(
    @field:NotBlank(message = "null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @field:Size(max = 50, message = "최대 50자까지 가능합니다.")
    val title: String,

    @field:NotBlank(message = "null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @field:Size(max = 500, message = "최대 500자까지 가능합니다.")
    val content: String,

    @field:Size(max = 15, message = "최대 15자까지 가능합니다.")
    val tag: List<String>,

    @field:NotNull(message = "null을 허용하지 않습니다.")
    val recruitment: Int,

    @field:NotNull(message = "null 허용하지 않습니다.")
    val startDay: LocalDate,

    @field:NotNull(message = "null을 를 허용하지 않습니다.")
    val endDay: LocalDate,
)
