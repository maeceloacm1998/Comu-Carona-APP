package com.app.comu_carona.feature.rideinprogressDetails.ui

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
import com.app.comu_carona.components.contenterror.CCErrorContentRetry
import com.app.comu_carona.components.contentloading.CCLoadingContent
import com.app.comu_carona.components.contentsuccess.CCSuccessContent
import com.app.comu_carona.feature.rideinprogressDetails.ui.RideInProgressDetailsViewModelEventState.OnGoToHome
import com.app.comu_carona.feature.rideinprogressDetails.ui.RideInProgressDetailsViewModelEventState.OnRetry
import com.app.comu_carona.feature.rideinprogressDetails.ui.RideInProgressDetailsViewModelUiState.HasCarRideDetails
import com.app.comu_carona.utils.AnimatedUtils.animatedTransitionPage
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun RideInProgressDetailsRoute(
    navController: NavController,
    backStackEntry: NavBackStackEntry
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val carRideId = backStackEntry.arguments?.getString("id") ?: return

    val viewModel: RideInProgressDetailsViewModel = koinViewModel(
        parameters = {
            parametersOf(carRideId, navController, snackbarHostState)
        }
    )
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    RideInProgressDetailsRoute(
        uiState = uiState,
        onEvent = viewModel::onEvent,
        snackbarHostState = snackbarHostState
    )
}

@Composable
fun RideInProgressDetailsRoute(
    uiState: RideInProgressDetailsViewModelUiState,
    onEvent: (RideInProgressDetailsViewModelEventState) -> Unit,
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
                    title = stringResource(R.string.ride_in_progress_details_complete_title),
                    description = stringResource(R.string.ride_in_progress_details_complete_description),
                    buttonText = stringResource(R.string.ride_in_progress_details_complete_button_title),
                    onClick = { onEvent(OnGoToHome) }
                )
            }

            else -> {
                CCLoadingContent(
                    isLoading = uiState.isLoading,
                    isError = uiState.isError,
                    loadingContent = {
                        RideInProgressDetailsScreenLoading()
                    },
                    errorContent = {
                        CCErrorContentRetry(
                            title = stringResource(R.string.generic_connection_error),
                            onClick = { onEvent(OnRetry) }
                        )
                    },
                    content = {
                        check(uiState is HasCarRideDetails)
                        RideInProgressDetailsScreen(
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