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
        uiState = uiState,
        onEvent = chatViewModel::onEvent
    )
}

@Composable
fun CheckCodeRoute(
    uiState: CheckCodeViewModelUiState,
    onEvent: (CheckCodeViewModelEventState) -> Unit
) {
    check(uiState is CheckCodeViewModelUiState.Code)
    CheckCodeScreen(
        uiState = uiState,
        onEvent = onEvent
    )
}