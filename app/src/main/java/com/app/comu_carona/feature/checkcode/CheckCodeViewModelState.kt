package com.app.comu_carona.feature.checkcode

/**
 * Represents the UI state of the chat screen.
 */
sealed interface CheckCodeViewModelUiState {

    /**
     * Represents the state when the chat is loading.
     */
    data class Code(
        val code: String
    ) : CheckCodeViewModelUiState
}

/**
 * Represents the state of the chat screen.
 */
data class CheckCodeViewModelState(
    val messageList: String = "",
    val isLoading: Boolean = false,
) {

    /**
     * Converts the state to a UI state.
     */
    fun toUiState(): CheckCodeViewModelUiState = CheckCodeViewModelUiState.Code(
        code = messageList
    )
}