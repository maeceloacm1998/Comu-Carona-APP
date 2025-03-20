package com.app.comu_carona.feature.home.steps.profile.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ProfileScreen(
    uiState: ProfileViewModelUiState,
    onEvent: (ProfileViewModelEventState) -> Unit
) {
    Text("dale")
}

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(
        uiState = ProfileViewModelUiState.HasProfile(
            profileInformation = null,
            isLoading = false,
            isError = false,
            isSuccess = false
        ),
        onEvent = {}
    )
}