package com.app.comu_carona.feature.home.steps.profile.ui

import com.app.comu_carona.feature.home.steps.profile.ui.ProfileViewModelUiState.HasProfile
import com.app.comu_carona.feature.registeraccount.data.models.RegisterAccountRequest


sealed interface ProfileViewModelUiState{
    data class HasProfile(
        val profileInformation: RegisterAccountRequest?,
        val isLoading: Boolean,
        val isError: Boolean,
        val isSuccess: Boolean,
    ) : ProfileViewModelUiState
}

data class ProfileViewModelState(
    val profileInformation: RegisterAccountRequest? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
) {
    fun toUiState(): ProfileViewModelUiState = HasProfile(
        profileInformation = profileInformation,
        isLoading = isLoading,
        isError = isError,
        isSuccess = isSuccess,
    )
}
