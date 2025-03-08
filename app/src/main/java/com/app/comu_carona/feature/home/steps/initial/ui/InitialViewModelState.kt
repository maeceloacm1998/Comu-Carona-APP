package com.app.comu_carona.feature.home.steps.initial.ui

import com.app.comu_carona.feature.home.steps.initial.data.models.AvailableCarRide
import com.app.comu_carona.feature.home.steps.initial.ui.InitialViewModelUiState.HasAvailableCarRide

/**
 * Represents the UI state of the Initial screen.
 */
sealed interface InitialViewModelUiState {
    /**
     * Represents the state when the Initial screen is loading.
     */
    data class HasAvailableCarRide(
        val availableCarRideList: List<AvailableCarRide>,
        val userName: String,
        val photoUrl: String,
        val isLoading: Boolean,
        val isError: Boolean,
        val isRefresh: Boolean,
        val isSuccess: Boolean,
    ) : InitialViewModelUiState
}

/**
 * Represents the state of the Initial screen.
 */
data class InitialViewModelState(
    val availableCarRideList: List<AvailableCarRide> = emptyList(),
    val userName: String = "",
    val photoUrl: String = "",
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isRefresh: Boolean = false,
    val isSuccess: Boolean = false,
) {

    /**
     * Converts the state to a UI state.
     */
    fun toUiState(): InitialViewModelUiState = HasAvailableCarRide(
        availableCarRideList = availableCarRideList,
        userName = userName,
        photoUrl = photoUrl,
        isLoading = isLoading,
        isError = isError,
        isRefresh = isRefresh,
        isSuccess = isSuccess,
    )
}
