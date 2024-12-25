package com.app.comu_carona.feature.home.ui

import com.app.comu_carona.feature.home.ui.HomeViewModelUiState.HasAvailableCarRide
import com.app.comu_carona.feature.home.data.models.AvailableCarRide

/**
 * Represents the UI state of the home screen.
 */
sealed interface HomeViewModelUiState {
    /**
     * Represents the state when the home screen is loading.
     */
    data class HasAvailableCarRide(
        val availableCarRideList: List<AvailableCarRide>,
        val userName: String,
        val photoUrl: String,
        val isLoading: Boolean,
        val isError: Boolean,
        val isRefresh: Boolean,
        val isSuccess: Boolean,
    ) : HomeViewModelUiState
}

/**
 * Represents the state of the home screen.
 */
data class HomeViewModelState(
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
    fun toUiState(): HomeViewModelUiState = HasAvailableCarRide(
        availableCarRideList = availableCarRideList,
        userName = userName,
        photoUrl = photoUrl,
        isLoading = isLoading,
        isError = isError,
        isRefresh = isRefresh,
        isSuccess = isSuccess,
    )
}
