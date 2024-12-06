package com.app.comu_carona.feature.registeraccount.data.models

data class RegisterAccountRequest(
    val fullName: String,
    val birthDate: String,
    val phoneNumber: String,
    val photoUrl: String = "XXX"
)