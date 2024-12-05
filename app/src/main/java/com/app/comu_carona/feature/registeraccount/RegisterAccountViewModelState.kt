package com.app.comu_carona.feature.registeraccount

/**
 * Represents the UI state of the register account screen.
 */
sealed interface RegisterAccountViewModelUiState {

    /**
     * Represents the state when the chat is loading.
     */
    data class Code(
        val code: List<String>,
        val isLoading: Boolean,
        val isError: Boolean,
        val isSuccess: Boolean,
    ) : RegisterAccountViewModelUiState
}

/**
 * Represents the state of the chat screen.
 */
data class RegisterAccountViewModelState(
    val code: List<String> = List(5) { "" },
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
) {

    /**
     * Converts the state to a UI state.
     */
    fun toUiState(): RegisterAccountViewModelUiState = RegisterAccountViewModelUiState.Code(
        code = code,
        isLoading = isLoading,
        isError = isError,
        isSuccess = isSuccess,
    )
}