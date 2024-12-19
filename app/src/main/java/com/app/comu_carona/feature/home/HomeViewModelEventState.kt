package com.app.comu_carona.feature.home

sealed class HomeViewModelEventState {
    data object OnLoadAvailableCarRide: HomeViewModelEventState()
}