package com.app.comu_carona.feature.home.steps.rideinprogress.data.models

data class RideInProgressModel(
    val uuid: String,
    val waitingAddress: String,
    val destinationAddress: String,
    val waitingHour: String,
    val destinationHour: String,
    val states: List<String>,
    val riderInformation: RideInProgressInformationModel,
    val isMyCarRide: Boolean
)

data class RideInProgressInformationModel(
    val username: String,
    val birthDate: String,
    val phoneNumber: String,
    val photoUrl: String,
)