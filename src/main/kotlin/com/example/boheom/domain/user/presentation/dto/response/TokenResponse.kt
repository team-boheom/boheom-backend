package com.example.boheom.domain.user.presentation.dto.response

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String,
)
