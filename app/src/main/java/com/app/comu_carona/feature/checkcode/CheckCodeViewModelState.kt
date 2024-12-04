package com.app.comu_carona.feature.checkcode

/**
 * Represents the UI state of the chat screen.
 */
sealed interface CheckCodeViewModelUiState {

    /**
     * Represents the state when the chat is loading.
     */
    data class Code(
        val code: List<String>,
        val isLoading: Boolean,
        val isError: Boolean,
        val isSuccess: Boolean,
    ) : CheckCodeViewModelUiState
}

/**
 * Represents the state of the chat screen.
 */
data class CheckCodeViewModelState(
    val code: List<String> = List(5) { "" },
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
) {

    /**
     * Converts the state to a UI state.
     */
    fun toUiState(): CheckCodeViewModelUiState = CheckCodeViewModelUiState.Code(
        code = code,
        isLoading = isLoading,
        isError = isError,
        isSuccess = isSuccess,
    )
}