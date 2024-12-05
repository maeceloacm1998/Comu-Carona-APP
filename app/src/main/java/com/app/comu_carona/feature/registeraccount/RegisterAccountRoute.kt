package com.app.comu_carona.feature.registeraccount

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterAccountRoute() {
    val registerAccountViewModel: RegisterAccountViewModel = koinViewModel()
    val uiState by registerAccountViewModel.uiState.collectAsStateWithLifecycle()

    RegisterAccountRoute(
        uiState = uiState,
    )
}

@Composable
fun RegisterAccountRoute(
    uiState: RegisterAccountViewModelUiState,
) {
    StageOfFullNameScreen()
}