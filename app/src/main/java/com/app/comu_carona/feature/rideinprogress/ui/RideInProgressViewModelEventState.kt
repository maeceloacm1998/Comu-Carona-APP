package com.app.comu_carona.feature.rideinprogress.ui

sealed class RideInProgressViewModelEventState {
    data object OnLoadRideInProgress: RideInProgressViewModelEventState()
    data class OnNavigateTo(val route: String): RideInProgressViewModelEventState()
}