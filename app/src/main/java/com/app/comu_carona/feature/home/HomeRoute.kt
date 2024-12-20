package com.app.comu_carona.feature.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import org.koin.androidx.compose.koinViewModel


@Composable
fun HomeRoute() {
    val viewModel: HomeViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        uiState = uiState,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun HomeScreen(
    uiState: HomeViewModelUiState,
    onEvent: (HomeViewModelEventState) -> Unit
) {

    HomeLoadingContent(
        isLoading = uiState.isLoading,
        isRefresh = uiState.isRefresh,
        isError = uiState.isError,
        onRefresh = { onEvent(HomeViewModelEventState.OnLoadAvailableCarRide) },
        loadingContent = { },
        errorContent = {  },
        content = { }
    )
}

@Composable
private fun HomeLoadingContent(
    isLoading: Boolean,
    isRefresh: Boolean,
    isError: Boolean,
    onRefresh: () -> Unit,
    loadingContent: @Composable () -> Unit,
    errorContent: @Composable () -> Unit,
    content: @Composable () -> Unit
) {

    when {
        isLoading -> loadingContent()
        isError -> errorContent()
        else -> SwipeRefresh(
            state = rememberSwipeRefreshState(isRefresh),
            onRefresh = onRefresh,
            content = content
        )
    }
}