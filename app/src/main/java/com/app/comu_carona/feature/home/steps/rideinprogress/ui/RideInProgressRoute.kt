package com.app.comu_carona.feature.home.steps.rideinprogress.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.app.comu_carona.R
import com.app.comu_carona.components.contenterror.CCErrorContentRetry
import com.app.comu_carona.components.contentloading.CCLoadingSwipeRefreshContent
import com.app.comu_carona.feature.home.steps.rideinprogress.ui.RideInProgressViewModelEventState.OnLoadRideInProgress
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
        uiState = uiState,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun RideInProgressRoute(
    uiState: RideInProgressViewModelUiState,
    onEvent: (RideInProgressViewModelEventState) -> Unit
) {
    CCLoadingSwipeRefreshContent(
        isLoading = uiState.isLoading,
        isRefresh = uiState.isRefresh,
        isError = uiState.isError,
        onRefresh = { onEvent(OnLoadRideInProgress) },
        loadingContent = {
            RideInProgressScreenLoading()
        },
        errorContent = {
            CCErrorContentRetry(
                title = stringResource(R.string.generic_connection_error),
                onClick = { onEvent(OnLoadRideInProgress) }
            )
        },
        content = {
            RideInProgressScreen(
                uiState = uiState,
                onEvent = onEvent
            )
        }
    )
}