package com.app.comu_carona.feature.createcarride.data.models

data class CreateCarRideRequest(
    val carModel: String,
    val carColor: String,
    val carPlate: String,
    val quantitySeats: Int,
    val waitingAddress: String,
    val destinationAddress: String,
    val waitingHour: String,
    val destinationHour: String,
    val status: String,
    val isTwoPassengersBehind: Boolean,
    val twoPassengersBehind: Boolean
)