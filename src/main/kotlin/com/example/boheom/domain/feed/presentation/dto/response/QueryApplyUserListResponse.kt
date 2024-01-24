package com.example.boheom.domain.feed.presentation.dto.response

import com.example.boheom.domain.user.presentation.dto.response.UserElement

data class QueryApplyUserListResponse(
    val users: List<UserElement>
)