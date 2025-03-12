package com.app.comu_carona.feature.carridedetails.ui

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.app.comu_carona.components.loadingcontent.CCLoadingContent
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun CarRideDetailsRoute(
    navController: NavController,
    backStackEntry: NavBackStackEntry
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val carRideId = backStackEntry.arguments?.getString("id") ?: return
    val viewModel: CarRideDetailsViewModel = koinViewModel(
        parameters = {
            parametersOf(carRideId, navController, snackbarHostState)
        }
    )
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CarRideDetailsRoute(
        uiState = uiState,
        onEvent = viewModel::onEvent,
        snackbarHostState = snackbarHostState
    )
}

@Composable
fun CarRideDetailsRoute(
    uiState: CarRideDetailsViewModelUiState,
    onEvent: (CarRideDetailsViewModelEventState) -> Unit,
    snackbarHostState: SnackbarHostState
) {
    CCLoadingContent(
        isLoading = uiState.isLoading,
        isError = uiState.isError,
        loadingContent = {
            // Colocar tela de loading
        },
        errorContent = { },
        content = {
            check(uiState is CarRideDetailsViewModelUiState.HasCarRideDetails)
            CarRideDetailsScreen(
                uiState = uiState,
                onEvent = onEvent,
                snackbarHostState = snackbarHostState
            )
        }
    )
}