package com.app.comu_carona.feature.myrideinprogressdetails.ui


sealed class MyRideInProgressDetailsViewModelEventState {
    /**
     * Reservation car ride
     */
    data object OnCancelMyRide : MyRideInProgressDetailsViewModelEventState()

    /**
     * Back to previous screen
     */
    data object OnBack : MyRideInProgressDetailsViewModelEventState()

    /**
     * Dismiss bottom sheet
     */
    data object OnDismissBottomSheet : MyRideInProgressDetailsViewModelEventState()


    /**
     * Open bottom sheet with car ride
     */
    data object OnOpenBottomSheet : MyRideInProgressDetailsViewModelEventState()

    /**
     * Call to WhatsApp
     */
    data object OnCallWhatsApp : MyRideInProgressDetailsViewModelEventState()

    /**
     * Call to Phone
     */
    data object OnCallPhone : MyRideInProgressDetailsViewModelEventState()

    /**
     * Go to home screen
     */
    data object OnGoToHome : MyRideInProgressDetailsViewModelEventState()

    /**
     * Retry fetch car ride details
     */
    data object OnRetry : MyRideInProgressDetailsViewModelEventState()

    /**
     * Open share link
     */
    data object OnOpenShare: MyRideInProgressDetailsViewModelEventState()

    /**
     * Open cancel bottom sheet
     */
    data object OnOpenCancelBottomSheet: MyRideInProgressDetailsViewModelEventState()
}