package com.example.boheom.feed.presentation.dto.request

import java.time.LocalDate
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class UpdateFeedRequest(
    @field:Size(max = 50, message = "최대 50자까지 가능합니다.")
    val title: String,

    @field:Size(max = 500, message = "최대 500자까지 가능합니다.")
    val content: String,

    @field:Size(max = 15, message = "최대 15자까지 가능합니다.")
    val tag: String,

    val recruitment: Int,

    val startDay: LocalDate,

    val endDay: LocalDate,
)
