package com.app.comu_carona.feature.home.steps.rideinprogress.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.app.comu_carona.R
import com.app.comu_carona.components.errorcontent.CCErrorContent
import com.app.comu_carona.components.loadingcontent.CCLoadingSwipeRefreshContent
import com.app.comu_carona.feature.home.steps.rideinprogress.ui.RideInProgressViewModelEventState.OnLoadRideInProgress
import com.app.comu_carona.feature.home.steps.rideinprogress.ui.RideInProgressViewModelUiState.HasRiderInProgress
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
            // Colocar tela de loading
            Text("deu loading")
        },
        errorContent = {
            Text("deu erro")
        },
        content = {
            if(uiState is HasRiderInProgress) {
                RideInProgressScreen(
                    uiState = uiState,
                    onEvent = onEvent
                )
            } else {
                CCErrorContent(
                    modifier = Modifier.fillMaxSize(),
                    title = stringResource(R.string.ride_in_progress_empty_title)
                )
            }
        }
    )
}