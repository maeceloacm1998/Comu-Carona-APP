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

    /**
     * Dismiss bottom sheet
     */
    data object OnDismissBottomSheet : CarRideDetailsViewModelEventState()


    /**
     * Open bottom sheet with car ride
     */
    data object OnOpenBottomSheet : CarRideDetailsViewModelEventState()

    /**
     * Call to WhatsApp
     */
    data object OnCallWhatsApp : CarRideDetailsViewModelEventState()

    /**
     * Call to Phone
     */
    data object OnCallPhone : CarRideDetailsViewModelEventState()

    /**
     * Go to home screen
     */
    data object OnGoToHome : CarRideDetailsViewModelEventState()

    /**
     * Retry fetch car ride details
     */
    data object OnRetry : CarRideDetailsViewModelEventState()

    /**
     * Open share link
     */
    data object OnOpenShare: CarRideDetailsViewModelEventState()
}