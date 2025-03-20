package com.app.comu_carona.feature.profiledetails

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.app.comu_carona.commons.extensions.decodeParameter
import com.app.comu_carona.feature.profiledetails.ProfileDetailsViewModelUiState.HasProfileDetails
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun ProfileDetailsRoute(
    navController: NavController,
    backStackEntry: NavBackStackEntry
) {
    val snackbarHostState = remember { SnackbarHostState() }

    val userName = backStackEntry.arguments?.getString("username") ?: return
    val birthDate = backStackEntry.arguments?.getString("birthDate") ?: return
    val phoneNumber = backStackEntry.arguments?.getString("phoneNumber") ?: return

    val viewModel: ProfileDetailsViewModel = koinViewModel(parameters = {
        parametersOf(
            navController,
            snackbarHostState,
            userName.decodeParameter(),
            birthDate.decodeParameter(),
            phoneNumber.decodeParameter()
        )
    })
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ProfileDetailsRoute(
        uiState = uiState,
        onEvent = viewModel::onEvent,
        snackbarHostState = snackbarHostState
    )
}

@Composable
fun ProfileDetailsRoute(
    uiState: ProfileDetailsViewModelUiState,
    onEvent: (ProfileDetailsViewModelEventState) -> Unit,
    snackbarHostState: SnackbarHostState
) {
    check(uiState is HasProfileDetails)

    ProfileDetailsScreen(
        uiState = uiState,
        onEvent = onEvent,
        snackbarHostState = snackbarHostState
    )
}