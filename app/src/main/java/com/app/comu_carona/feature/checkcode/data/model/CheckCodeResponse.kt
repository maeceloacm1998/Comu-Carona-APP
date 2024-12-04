package com.app.comu_carona.feature.checkcode.data.model

data class CheckCodeResponse(
    val username: String,
    val authenticated: Boolean,
    val created: String,
    val expiration: String,
    val accessToken: String,
    val refreshToken: String
)