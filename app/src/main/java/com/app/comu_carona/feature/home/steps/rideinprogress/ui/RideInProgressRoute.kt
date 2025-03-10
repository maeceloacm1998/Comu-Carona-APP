package com.app.comu_carona.feature.home.steps.rideinprogress.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.app.comu_carona.components.loadingcontent.CCLoadingSwipeRefreshContent
import com.app.comu_carona.feature.home.steps.initial.ui.InitialScreenLoading
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
            InitialScreenLoading(
                userName = "",
                photoUrl = ""
            )
        },
        errorContent = {
            Text("deu erro")
        },
        content = {
            RideInProgressScreen(
                uiState = uiState,
                onEvent = onEvent
            )
        }
    )
}