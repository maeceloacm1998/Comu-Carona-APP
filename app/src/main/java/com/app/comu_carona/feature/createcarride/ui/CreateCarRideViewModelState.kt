package com.app.comu_carona.feature.createcarride.ui

import com.app.comu_carona.feature.createcarride.data.models.CreateCarRideSteps

sealed interface CreateCarRideViewModelUiState {

    data class Steps(
        val steps: CreateCarRideSteps,
        val carModel: String,
        val carColor: String,
        val carPlate: String,
        val quantitySeats: Int,
        var waitingAddress: String,
        var destinationAddress: String,
        var waitingHour: String,
        var destinationHour: String,
        var enabledCarModelScreen: Boolean,
        val isLoading: Boolean,
        val isError: Boolean,
        val isSuccess: Boolean,
    ) : CreateCarRideViewModelUiState
}

data class CreateCarRideViewModelState(
    val steps: CreateCarRideSteps = CreateCarRideSteps.CAR_MODEL,
    val carModel: String = "",
    val carColor: String = "",
    val carPlate: String = "",
    val quantitySeats: Int = 0,
    var waitingAddress: String = "",
    var destinationAddress: String = "",
    var waitingHour: String = "",
    var destinationHour: String = "",
    var enabledCarModelScreen: Boolean = false,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
) {

    fun toUiState(): CreateCarRideViewModelUiState = CreateCarRideViewModelUiState.Steps(
        steps = steps,
        carModel = carModel,
        carColor = carColor,
        carPlate = carPlate,
        quantitySeats = quantitySeats,
        waitingAddress = waitingAddress,
        destinationAddress = destinationAddress,
        waitingHour = waitingHour,
        destinationHour = destinationHour,
        enabledCarModelScreen = enabledCarModelScreen,
        isLoading = isLoading,
        isError = isError,
        isSuccess = isSuccess,
    )
}