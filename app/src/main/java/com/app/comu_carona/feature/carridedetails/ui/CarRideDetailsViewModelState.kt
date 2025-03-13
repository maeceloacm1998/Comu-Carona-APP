package com.app.comu_carona.feature.carridedetails.ui

import com.app.comu_carona.components.snackbar.SnackbarCustomType
import com.app.comu_carona.feature.carridedetails.data.models.CarRideDetails

sealed interface CarRideDetailsViewModelUiState {
    val isLoading: Boolean
    val isError: Boolean
    val isLoadingReservation: Boolean
    val isSuccessReservation: Boolean
    val showSnackBar: Boolean
    val snackbarType: SnackbarCustomType
    val showBottomSheet: Boolean

    data class HasCarRideDetails(
        val carRideDetailsResponse: CarRideDetails?,
        override val showSnackBar: Boolean,
        override val snackbarType: SnackbarCustomType,
        override val showBottomSheet: Boolean,
        override val isLoading: Boolean,
        override val isError: Boolean,
        override val isLoadingReservation: Boolean,
        override val isSuccessReservation: Boolean
    ) : CarRideDetailsViewModelUiState
}

data class CarRideDetailsViewModelState(
    val carRideDetailsResponse: CarRideDetails? = null,
    val showSnackBar: Boolean = false,
    val snackbarType: SnackbarCustomType = SnackbarCustomType.SUCCESS,
    val showBottomSheet: Boolean = false,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isLoadingReservation: Boolean = false,
    val isSuccessReservation: Boolean = false,
) {

    fun toUiState(): CarRideDetailsViewModelUiState =
        CarRideDetailsViewModelUiState.HasCarRideDetails(
            carRideDetailsResponse = carRideDetailsResponse,
            showSnackBar = showSnackBar,
            snackbarType = snackbarType,
            showBottomSheet = showBottomSheet,
            isLoading = isLoading,
            isError = isError,
            isLoadingReservation = isLoadingReservation,
            isSuccessReservation = isSuccessReservation
        )
}