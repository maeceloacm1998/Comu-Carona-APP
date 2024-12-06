package com.app.comu_carona.feature.registeraccount.data.models

data class RegisterAccountResponse(
    val username: String,
    val authenticated: Boolean,
    val created: String,
    val expiration: String,
    val accessToken: String,
    val refreshToken: String
)