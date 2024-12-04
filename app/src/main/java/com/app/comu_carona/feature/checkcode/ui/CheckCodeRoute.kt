package com.app.comu_carona.feature.checkcode.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun CheckCodeRoute(
    navController: NavController
) {
    val chatViewModel: CheckCodeViewModel = koinViewModel(parameters = {
        parametersOf(navController)
    })
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