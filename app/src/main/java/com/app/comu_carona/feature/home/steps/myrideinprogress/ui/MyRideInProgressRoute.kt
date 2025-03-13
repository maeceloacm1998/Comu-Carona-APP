package com.app.comu_carona.feature.home.steps.myrideinprogress.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.app.comu_carona.R
import com.app.comu_carona.components.contenterror.CCErrorContentRetry
import com.app.comu_carona.components.contentloading.CCLoadingSwipeRefreshContent
import com.app.comu_carona.feature.home.steps.myrideinprogress.ui.MyRideInProgressViewModelEventState.OnLoadMyRideInProgress
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun MyRideInProgressRoute(
    navController: NavController
) {
    val viewModel: MyRideInProgressViewModel = koinViewModel(
        parameters = {
            parametersOf(navController)
        }
    )
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    MyRideInProgressRoute(
        uiState = uiState,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun MyRideInProgressRoute(
    uiState: MyRideInProgressViewModelUiState,
    onEvent: (MyRideInProgressViewModelEventState) -> Unit
) {
    CCLoadingSwipeRefreshContent(
        isLoading = uiState.isLoading,
        isRefresh = uiState.isRefresh,
        isError = uiState.isError,
        onRefresh = { onEvent(OnLoadMyRideInProgress) },
        loadingContent = {
            MyRideInProgressScreenLoading()
        },
        errorContent = {
            CCErrorContentRetry(
                title = stringResource(R.string.generic_connection_error),
                onClick = { onEvent(OnLoadMyRideInProgress) }
            )
        },
        content = {
            MyRideInProgressScreen(
                uiState = uiState,
                onEvent = onEvent
            )
        }
    )
}