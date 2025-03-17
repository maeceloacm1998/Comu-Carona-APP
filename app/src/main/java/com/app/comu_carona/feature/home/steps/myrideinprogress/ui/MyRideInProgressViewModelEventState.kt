package com.app.comu_carona.feature.home.steps.myrideinprogress.ui

import com.app.comu_carona.feature.home.steps.rideinprogress.data.models.RideInProgressFilterOptions

sealed class MyRideInProgressViewModelEventState {
    data object OnLoadMyRideInProgress : MyRideInProgressViewModelEventState()
    data class OnSelectFilter(val rideInProgressFilterOptions: RideInProgressFilterOptions) :
        MyRideInProgressViewModelEventState()

    data class OnNavigateTo(val route: String, val params: String = "") :
        MyRideInProgressViewModelEventState()
}