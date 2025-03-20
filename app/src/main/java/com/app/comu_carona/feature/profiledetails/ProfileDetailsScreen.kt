package com.app.comu_carona.feature.profiledetails

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ProfileDetailsScreen(
    uiState: ProfileDetailsViewModelUiState,
    onEvent: (ProfileDetailsViewModelEventState) -> Unit
) {
    Text("dale")
}

@Preview
@Composable
fun ProfileDetailsScreenPreview() {
    ProfileDetailsScreen(
        uiState = ProfileDetailsViewModelState().toUiState(),
        onEvent = {}
    )
}