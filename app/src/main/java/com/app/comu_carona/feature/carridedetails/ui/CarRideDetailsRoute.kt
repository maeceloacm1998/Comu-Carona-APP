package com.app.comu_carona.feature.carridedetails.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.app.comu_carona.R
import com.app.comu_carona.components.contentloading.CCLoadingContent
import com.app.comu_carona.components.contentsuccess.CCSuccessContent
import com.app.comu_carona.feature.carridedetails.ui.CarRideDetailsViewModelEventState.OnGoToHome
import com.app.comu_carona.utils.AnimatedUtils.animatedTransitionPage
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
    AnimatedContent(
        targetState = uiState.isSuccessReservation,
        label = "AnimatedContent",
        transitionSpec = animatedTransitionPage()
    ) { targetState ->
        when (targetState) {
            true -> {
                CCSuccessContent(
                    title = stringResource(R.string.car_ride_details_complete_title),
                    description = stringResource(R.string.car_ride_details_complete_description),
                    buttonText = stringResource(R.string.car_ride_details_complete_button_title),
                    onClick = { onEvent(OnGoToHome) }
                )
            }

            else -> {
                CCLoadingContent(
                    isLoading = uiState.isLoading,
                    isError = uiState.isError,
                    loadingContent = {
                        // Colocar tela de loading
                    },
                    errorContent = {
                        // Error content
                    },
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
        }
    }
}