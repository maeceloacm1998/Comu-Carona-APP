package com.app.comu_carona.feature.carridedetails.ui

sealed class CarRideDetailsViewModelEventState {
    /**
     * Fetch car ride details data object
     */
    data object OnFetchReservationRide : CarRideDetailsViewModelEventState()

    /**
     * Reservation car ride
     */
    data object OnReservationRide : CarRideDetailsViewModelEventState()

    /**
     * Back to previous screen
     */
    data object OnBack : CarRideDetailsViewModelEventState()
}