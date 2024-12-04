package com.app.comu_carona.feature.checkcode

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CheckCodeRoute() {
    val chatViewModel: CheckCodeViewModel = viewModel()
    val uiState by chatViewModel.uiState.collectAsStateWithLifecycle()

    CheckCodeRoute(
        uiState = uiState
    )
}

@Composable
fun CheckCodeRoute(
    uiState: CheckCodeViewModelUiState
) {
    check(uiState is CheckCodeViewModelUiState.Code)
    CheckCodeScreen(
        uiState = uiState
    )
}