package com.app.comu_carona.feature.home.steps.rideinprogress.ui

import com.app.comu_carona.feature.home.steps.rideinprogress.data.models.RideInProgressFilterOptions

sealed class RideInProgressViewModelEventState {
    data object OnLoadRideInProgress : RideInProgressViewModelEventState()
    data class OnSelectFilter(val rideInProgressFilterOptions: RideInProgressFilterOptions) :
        RideInProgressViewModelEventState()

    data class OnNavigateTo(val route: String) : RideInProgressViewModelEventState()
}