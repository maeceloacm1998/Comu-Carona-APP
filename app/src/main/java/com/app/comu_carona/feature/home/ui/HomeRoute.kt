package com.app.comu_carona.feature.home.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf


@Composable
fun HomeRoute(
    navController: NavController
) {
    val viewModel: HomeViewModel = koinViewModel(parameters = {
        parametersOf(navController)
    })
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeRoute(
        uiState = uiState,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun HomeRoute(
    uiState: HomeViewModelUiState,
    onEvent: (HomeViewModelEventState) -> Unit
) {
    check(uiState is HomeViewModelUiState.HasAvailableCarRide)

    HomeLoadingContent(
        isLoading = uiState.isLoading,
        isRefresh = uiState.isRefresh,
        isError = uiState.isError,
        onRefresh = { onEvent(HomeViewModelEventState.OnLoadAvailableCarRide) },
        loadingContent = { HomeScreenLoading(
            userName = uiState.userName,
            photoUrl = uiState.photoUrl
        ) },
        errorContent = { },
        content = {
            HomeScreen(
                uiState = uiState,
                onEvent = onEvent
            )
        }
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

    AnimatedVisibility(
        visible = isLoading,
        modifier = Modifier.fillMaxSize()
    ) {
        loadingContent()
    }

    AnimatedVisibility(
        visible = isError,
        modifier = Modifier.fillMaxSize()
    ) {
        errorContent()
    }

    AnimatedVisibility(
        visible = !isLoading && !isError,
        modifier = Modifier.fillMaxSize()
    ) {
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefresh),
            onRefresh = onRefresh,
            content = content
        )
    }
}