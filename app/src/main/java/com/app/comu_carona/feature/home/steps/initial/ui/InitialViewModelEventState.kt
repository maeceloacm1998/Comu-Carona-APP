package com.app.comu_carona.feature.home.steps.initial.ui

sealed class InitialViewModelEventState {
    data object OnLoadAvailableCarRide : InitialViewModelEventState()
    data class OnNavigateTo(val route: String, val params: String = "") :
        InitialViewModelEventState()
}