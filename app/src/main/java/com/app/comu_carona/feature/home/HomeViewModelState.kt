package com.app.comu_carona.feature.home

import com.app.comu_carona.routes.Routes

/**
 * Represents the UI state of the home screen.
 */
sealed interface HomeViewModelUiState {
    /**
     * Represents the state when the home screen is loading.
     */
    data class Data(
        val steps: String,
    ) : HomeViewModelUiState
}

/**
 * Represents the state of the home screen.
 */
data class HomeViewModelState(
    val steps: String = Routes.Initial.route
) {

    /**
     * Converts the state to a UI state.
     */
    fun toUiState(): HomeViewModelUiState = HomeViewModelUiState.Data(
        steps = steps
    )
}
