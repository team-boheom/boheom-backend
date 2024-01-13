package com.example.boheom.user.presentation.dto.response

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String,
)
