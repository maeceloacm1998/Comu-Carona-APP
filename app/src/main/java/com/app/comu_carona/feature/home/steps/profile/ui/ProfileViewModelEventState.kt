package com.app.comu_carona.feature.home.steps.profile.ui

sealed class ProfileViewModelEventState {
    data object OnLoadProfile : ProfileViewModelEventState()
    data class OnNavigateTo(val route: String, val params: String = "") :
        ProfileViewModelEventState()
    data object OnLogout : ProfileViewModelEventState()
}