package com.app.comu_carona.feature.profiledetails

import com.app.comu_carona.feature.registeraccount.data.models.RegisterAccountRequest

sealed interface ProfileDetailsViewModelUiState{
    val isLoading: Boolean
    val isError: Boolean

    data class HasProfileDetails(
        val profileDetailsInformation: RegisterAccountRequest?,
        override val isLoading: Boolean,
        override val isError: Boolean
    ) : ProfileDetailsViewModelUiState
}

data class ProfileDetailsViewModelState(
    val profileDetailsInformation: RegisterAccountRequest? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false
) {
    fun toUiState(): ProfileDetailsViewModelUiState =
        ProfileDetailsViewModelUiState.HasProfileDetails(
            profileDetailsInformation = profileDetailsInformation,
            isLoading = isLoading,
            isError = isError
        )
}