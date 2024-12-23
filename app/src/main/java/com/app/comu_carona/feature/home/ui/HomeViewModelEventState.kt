package com.app.comu_carona.feature.home.ui

sealed class HomeViewModelEventState {
    data object OnLoadAvailableCarRide: HomeViewModelEventState()
    data class OnNavigateTo(val route: String): HomeViewModelEventState()
}