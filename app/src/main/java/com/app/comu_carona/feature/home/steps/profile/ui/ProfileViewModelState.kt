package com.app.comu_carona.feature.home.steps.profile.ui

import com.app.comu_carona.feature.home.steps.profile.ui.ProfileViewModelUiState.HasProfile
import com.app.comu_carona.feature.registeraccount.data.models.RegisterAccountRequest


sealed interface ProfileViewModelUiState{
    val isLoading: Boolean
    val isError: Boolean

    data class HasProfile(
        val profileInformation: RegisterAccountRequest?,
        override val isLoading: Boolean,
        override val isError: Boolean
    ) : ProfileViewModelUiState
}

data class ProfileViewModelState(
    val profileInformation: RegisterAccountRequest? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false
) {
    fun toUiState(): ProfileViewModelUiState = HasProfile(
        profileInformation = profileInformation,
        isLoading = isLoading,
        isError = isError
    )
}
