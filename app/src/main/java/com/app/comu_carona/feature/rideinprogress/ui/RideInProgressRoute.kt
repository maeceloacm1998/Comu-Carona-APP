package com.app.comu_carona.feature.rideinprogress.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun RideInProgressRoute(
    navController: NavController
) {
    val viewModel: RideInProgressViewModel = koinViewModel(
        parameters = {
            parametersOf(navController)
        }
    )
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    RideInProgressRoute(
        uiState = uiState
    )
}

@Composable
fun RideInProgressRoute(
    uiState: RideInProgressViewModelUiState
) {

}