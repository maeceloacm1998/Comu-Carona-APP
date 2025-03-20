package com.app.comu_carona.feature.profiledetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
    val userName = backStackEntry.arguments?.getString("username") ?: return
    val birthDate = backStackEntry.arguments?.getString("birthDate") ?: return
    val phoneNumber = backStackEntry.arguments?.getString("phoneNumber") ?: return
    val photoUrl = backStackEntry.arguments?.getString("photoUrl") ?: return

    val viewModel: ProfileDetailsViewModel = koinViewModel(parameters = {
        parametersOf(
            navController,
            userName.decodeParameter(),
            birthDate.decodeParameter(),
            phoneNumber.decodeParameter(),
            photoUrl.decodeParameter()
        )
    })
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ProfileDetailsRoute(
        uiState = uiState,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun ProfileDetailsRoute(
    uiState: ProfileDetailsViewModelUiState,
    onEvent: (ProfileDetailsViewModelEventState) -> Unit
) {
    check(uiState is HasProfileDetails)

    ProfileDetailsScreen(
        uiState = uiState,
        onEvent = onEvent
    )
}