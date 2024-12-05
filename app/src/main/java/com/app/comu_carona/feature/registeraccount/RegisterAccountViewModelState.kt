package com.app.comu_carona.feature.registeraccount

import com.app.comu_carona.feature.registeraccount.data.models.RegisterAccountSteps

/**
 * Represents the UI state of the register account screen.
 */
sealed interface RegisterAccountViewModelUiState {

    /**
     * Represents the state when the register account screen is loading.
     */
    data class Register(
        val steps: RegisterAccountSteps,
        val fullName:String,
        val birthDate:String,
        val phoneNumber:String,
        val isLoading: Boolean,
        val isError: Boolean,
        val isSuccess: Boolean,
    ) : RegisterAccountViewModelUiState
}

/**
 * Represents the state of the register account screen.
 */
data class RegisterAccountViewModelState(
    val steps: RegisterAccountSteps = RegisterAccountSteps.FULL_NAME,
    val fullName:String = "",
    val birthDate:String = "",
    val phoneNumber:String = "",
    val isGrantedPermission: Boolean = false,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
) {

    /**
     * Converts the state to a UI state.
     */
    fun toUiState(): RegisterAccountViewModelUiState = RegisterAccountViewModelUiState.Register(
        steps = steps,
        fullName = fullName,
        birthDate = birthDate,
        phoneNumber = phoneNumber,
        isLoading = isLoading,
        isError = isError,
        isSuccess = isSuccess,
    )
}