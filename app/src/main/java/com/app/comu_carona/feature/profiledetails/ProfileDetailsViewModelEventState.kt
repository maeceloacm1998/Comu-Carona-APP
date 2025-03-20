package com.app.comu_carona.feature.profiledetails

sealed class ProfileDetailsViewModelEventState {
    data object OnBack : ProfileDetailsViewModelEventState()
}