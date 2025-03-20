package com.app.comu_carona.feature.home.steps.profile.ui

sealed class ProfileViewModelEventState {
    data object OnLoadProfile : ProfileViewModelEventState()
    data object OnNavigateToProfileDetails : ProfileViewModelEventState()
    data object OnLogout : ProfileViewModelEventState()
}