package com.ilaftalkful.ihma.model

data class UserLoginResponse(
    val `data`: List<Data>,
    val status: Status
)

data class Data(
    val expires_at: String,
    val return_to_url: Any,
    val session_token: String,
    val status: String,
    val user: User
)

data class Status(
    val code: Int,
    val error: Boolean,
    val message: String,
    val type: String
)

data class User(
    val username: String
)