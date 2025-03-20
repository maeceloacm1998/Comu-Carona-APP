package com.app.comu_carona.feature.home.steps.profile.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.app.comu_carona.R
import com.app.comu_carona.components.contenterror.CCErrorContentRetry
import com.app.comu_carona.components.contentloading.CCLoadingContent
import com.app.comu_carona.feature.home.steps.profile.ui.ProfileViewModelEventState.OnLoadProfile
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun ProfileRoute(
    navController: NavController
) {
    val viewModel: ProfileViewModel = koinViewModel(parameters = {
        parametersOf(navController)
    })
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ProfileRoute(
        uiState = uiState,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun ProfileRoute(
    uiState: ProfileViewModelUiState,
    onEvent: (ProfileViewModelEventState) -> Unit
) {
    CCLoadingContent(
        isLoading = uiState.isLoading,
        isError = uiState.isError,
        loadingContent = {
            ProfileScreenLoading()
        },
        errorContent = {
            CCErrorContentRetry(
                title = stringResource(R.string.generic_connection_error),
                onClick = {
                    onEvent(OnLoadProfile)
                }
            )
        },
        content = {
            check(uiState is ProfileViewModelUiState.HasProfile)

            ProfileScreen(
                uiState = uiState,
                onEvent = onEvent
            )
        }
    )
}