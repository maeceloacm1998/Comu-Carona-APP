package com.app.comu_carona.feature.home

import com.app.comu_carona.feature.home.HomeViewModelUiState.HasAvailableCarRide
import com.app.comu_carona.feature.home.HomeViewModelUiState.NoHasAvailableCarRide
import com.app.comu_carona.feature.home.data.models.AvailableCarRide

/**
 * Represents the UI state of the home screen.
 */
sealed interface HomeViewModelUiState {
    val isLoading: Boolean
    val isError: Boolean
    val isRefresh: Boolean
    val isSuccess: Boolean

    data class NoHasAvailableCarRide(
        override val isLoading: Boolean,
        override val isError: Boolean,
        override val isRefresh: Boolean,
        override val isSuccess: Boolean,
    ) : HomeViewModelUiState

    /**
     * Represents the state when the home screen is loading.
     */
    data class HasAvailableCarRide(
        val availableCarRideList: List<AvailableCarRide>,
        override val isLoading: Boolean,
        override val isError: Boolean,
        override val isRefresh: Boolean,
        override val isSuccess: Boolean,
    ) : HomeViewModelUiState

}

/**
 * Represents the state of the home screen.
 */
data class HomeViewModelState(
    val availableCarRideList: List<AvailableCarRide> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isRefresh: Boolean = false,
    val isSuccess: Boolean = false,
) {

    /**
     * Converts the state to a UI state.
     */
    fun toUiState(): HomeViewModelUiState = if (availableCarRideList.isEmpty()) {
        NoHasAvailableCarRide(
            isLoading = isLoading,
            isError = isError,
            isRefresh = isRefresh,
            isSuccess = isSuccess,
        )
    } else {
        HasAvailableCarRide(
            availableCarRideList = availableCarRideList,
            isLoading = isLoading,
            isError = isError,
            isRefresh = isRefresh,
            isSuccess = isSuccess,
        )
    }
}
