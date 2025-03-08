package com.app.comu_carona.components.loadingcontent

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun CCLoadingSwipeRefreshContent(
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