package com.example.boheom.global.error.exception

enum class ErrorCode(
    val status: Int,
    val message: String,
) {
    UNMATCHED_PASSWORD(400, "Unmatched Password"),
    INCORRECT_PASSWORD(400, "Incorrect Password"),

    TOKEN_INVALID(401, "Token Invalid"),
    TOKEN_EXPIRED(401, "Token Expired"),

    USER_NOT_FOUND(404, "User Not Found"),

    ALREADY_ACCOUNT_ID(409, "Already Account Id"),
    ALREADY_NICKNAME(409, "Already Nickname"),

    INTERNAL_SERVER_ERROR(500, "Internal Server Error");
}