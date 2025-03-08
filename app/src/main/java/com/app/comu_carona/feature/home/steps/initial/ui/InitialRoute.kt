package com.app.comu_carona.feature.home.steps.initial.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.app.comu_carona.components.loadingcontent.CCLoadingSwipeRefreshContent
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun InitialRoute(
    navController: NavController
) {
    val viewModel: InitialViewModel = koinViewModel(parameters = {
        parametersOf(navController)
    })
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    InitialRoute(
        uiState = uiState,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun InitialRoute(
    uiState: InitialViewModelUiState,
    onEvent: (InitialViewModelEventState) -> Unit
) {
    check(uiState is InitialViewModelUiState.HasAvailableCarRide)

    CCLoadingSwipeRefreshContent(
        isLoading = uiState.isLoading,
        isRefresh = uiState.isRefresh,
        isError = uiState.isError,
        onRefresh = { onEvent(InitialViewModelEventState.OnLoadAvailableCarRide) },
        loadingContent = {
            InitialScreenLoading(
                userName = uiState.userName,
                photoUrl = uiState.photoUrl
            )
        },
        errorContent = { },
        content = {
            InitialScreen(
                uiState = uiState,
                onEvent = onEvent
            )
        }
    )
}