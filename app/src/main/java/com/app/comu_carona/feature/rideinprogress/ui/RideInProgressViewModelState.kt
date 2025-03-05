package com.app.comu_carona.feature.rideinprogress.ui

import com.app.comu_carona.feature.rideinprogress.data.models.RideInProgressModel

sealed interface RideInProgressViewModelUiState {
    val isLoading: Boolean
    val isError: Boolean
    val isRefresh: Boolean
    val isSuccess: Boolean

    /**
     * Represents the state when the home screen is loading.
     */
    data class NoHasRiderInProgress(
        override val isLoading: Boolean,
        override val isError: Boolean,
        override val isRefresh: Boolean,
        override val isSuccess: Boolean
    ) : RideInProgressViewModelUiState


    /**
     * Represents the state when the home screen is loading.
     */
    data class HasRiderInProgress(
        val rideInProgressList: List<RideInProgressModel>,
        val rideInProgressListFiltered: List<RideInProgressModel>,
        override val isLoading: Boolean,
        override val isError: Boolean,
        override val isRefresh: Boolean,
        override val isSuccess: Boolean,
    ) : RideInProgressViewModelUiState
}

/**
 * Represents the state of the home screen.
 */
data class RideInProgressViewModelState(
    val rideInProgressList: List<RideInProgressModel> = emptyList(),
    val rideInProgressListFiltered: List<RideInProgressModel> = emptyList(),
    val rideInProgressFilterSelected: String = "",
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isRefresh: Boolean = false,
    val isSuccess: Boolean = false,
) {

    /**
     * Converts the state to a UI state.
     */
    fun toUiState(): RideInProgressViewModelUiState = if (rideInProgressList.isNotEmpty()) {
        RideInProgressViewModelUiState.HasRiderInProgress(
            rideInProgressList = rideInProgressList,
            rideInProgressListFiltered = rideInProgressListFiltered,
            isLoading = isLoading,
            isError = isError,
            isRefresh = isRefresh,
            isSuccess = isSuccess
        )
    } else {
        RideInProgressViewModelUiState.NoHasRiderInProgress(
            isLoading = isLoading,
            isError = isError,
            isRefresh = isRefresh,
            isSuccess = isSuccess
        )
    }
}
