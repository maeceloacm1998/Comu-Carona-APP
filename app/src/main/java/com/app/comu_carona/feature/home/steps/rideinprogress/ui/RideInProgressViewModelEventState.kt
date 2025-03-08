package com.app.comu_carona.feature.home.steps.rideinprogress.ui

sealed class RideInProgressViewModelEventState {
    data object OnLoadRideInProgress: RideInProgressViewModelEventState()
    data class OnNavigateTo(val route: String): RideInProgressViewModelEventState()
}