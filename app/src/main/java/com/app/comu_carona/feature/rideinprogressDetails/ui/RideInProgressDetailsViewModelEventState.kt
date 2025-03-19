package com.app.comu_carona.feature.rideinprogressDetails.ui

sealed class RideInProgressDetailsViewModelEventState {
    /**
     * Reservation car ride
     */
    data object OnCancelMyRide : RideInProgressDetailsViewModelEventState()

    /**
     * Back to previous screen
     */
    data object OnBack : RideInProgressDetailsViewModelEventState()

    /**
     * Dismiss bottom sheet
     */
    data object OnDismissBottomSheet : RideInProgressDetailsViewModelEventState()


    /**
     * Open bottom sheet with car ride
     */
    data object OnOpenBottomSheet : RideInProgressDetailsViewModelEventState()

    /**
     * Call to WhatsApp
     */
    data object OnCallWhatsApp : RideInProgressDetailsViewModelEventState()

    /**
     * Call to Phone
     */
    data object OnCallPhone : RideInProgressDetailsViewModelEventState()

    /**
     * Go to home screen
     */
    data object OnGoToHome : RideInProgressDetailsViewModelEventState()

    /**
     * Retry fetch car ride details
     */
    data object OnRetry : RideInProgressDetailsViewModelEventState()

    /**
     * Open share link
     */
    data object OnOpenShare: RideInProgressDetailsViewModelEventState()

    /**
     * Open cancel bottom sheet
     */
    data object OnOpenCancelBottomSheet: RideInProgressDetailsViewModelEventState()
}