package com.app.comu_carona.feature.carridedetails.data.models

data class CarRideDetails(
    val id: String,
    val dateTitle: String,
    val description: String,
    var riderPhoto: String,
    val riderUsername: String,
    val riderDescription: String,
    val waitingAddress: String,
    val destinationAddress: String,
    val waitingHour: String,
    val destinationHour: String,
    val isFullSeats: Boolean,
    val reservations: List<Reservation>,
    val shareDeeplink: String,
    val bottomSheetCarRideUser: BottomSheetCarRideUser
)

data class Reservation(
    val username: String,
    val birthDate: String,
    val phoneNumber: String,
    val photoUrl: String
)

data class BottomSheetCarRideUser(
    val bottomSheetRiderPlate: String,
    val bottomSheetRiderUsername: String,
    val bottomSheetRiderDescription: String,
    val bottomSheetRiderPhoto: String,
    val bottomSheetCarRiderDescription: String,
    val bottomSheetRiderPhoneNumber: String
)