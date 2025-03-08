package com.app.comu_carona.feature.home

sealed class HomeViewModelEventState {
    data class OnNavigateTo(val route: String): HomeViewModelEventState()
}