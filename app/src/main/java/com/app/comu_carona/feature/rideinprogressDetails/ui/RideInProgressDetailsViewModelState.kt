package com.app.comu_carona.feature.rideinprogressDetails.ui

import com.app.comu_carona.components.snackbar.SnackbarCustomType
import com.app.comu_carona.feature.carridedetails.data.models.CarRideDetails

sealed interface RideInProgressDetailsViewModelUiState {
    val isLoading: Boolean
    val isError: Boolean
    val isLoadingReservation: Boolean
    val isSuccessReservation: Boolean
    val isEnableButton: Boolean
    val showSnackBar: Boolean
    val snackbarType: SnackbarCustomType
    val showBottomSheet: Boolean
    val showCancelBottomSheet: Boolean

    data class HasCarRideDetails(
        val carRideDetailsResponse: CarRideDetails?,
        override val showSnackBar: Boolean,
        override val snackbarType: SnackbarCustomType,
        override val showBottomSheet: Boolean,
        override val showCancelBottomSheet: Boolean,
        override val isLoading: Boolean,
        override val isError: Boolean,
        override val isLoadingReservation: Boolean,
        override val isSuccessReservation: Boolean,
        override val isEnableButton: Boolean,
    ) : RideInProgressDetailsViewModelUiState
}

data class RideInProgressDetailsViewModelState(
    val carRideDetailsResponse: CarRideDetails? = null,
    val showSnackBar: Boolean = false,
    val snackbarType: SnackbarCustomType = SnackbarCustomType.SUCCESS,
    val showBottomSheet: Boolean = false,
    val showCancelBottomSheet: Boolean = false,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isLoadingReservation: Boolean = false,
    val isSuccessReservation: Boolean = false,
    val isEnableButton: Boolean = true
) {

    fun toUiState(): RideInProgressDetailsViewModelUiState =
        RideInProgressDetailsViewModelUiState.HasCarRideDetails(
            carRideDetailsResponse = carRideDetailsResponse,
            showSnackBar = showSnackBar,
            snackbarType = snackbarType,
            showBottomSheet = showBottomSheet,
            showCancelBottomSheet = showCancelBottomSheet,
            isLoading = isLoading,
            isError = isError,
            isLoadingReservation = isLoadingReservation,
            isSuccessReservation = isSuccessReservation,
            isEnableButton = isEnableButton
        )
}