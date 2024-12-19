package com.app.comu_carona.feature.home.data.models

data class AvailableCarRide(
    val id: String,
    val waitingAddress: String,
    val destinationAddress: String,
    val waitingHour: String,
    val destinationHour: String,
    val riderPhotoUrl: String,
    val riderUserName: String,
    val carModel: String,
    val carColor: String
)