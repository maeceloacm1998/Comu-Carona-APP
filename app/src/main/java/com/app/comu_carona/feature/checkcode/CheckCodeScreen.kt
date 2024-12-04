package com.app.comu_carona.feature.checkcode

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CheckCodeScreen(
    uiState: CheckCodeViewModelUiState.Code
) {
    Text(
        text = "Code: ${uiState.code}"
    )
}

@Preview
@Composable
fun CheckCodeScreenPreview() {
    CheckCodeScreen(
        uiState = CheckCodeViewModelUiState.Code(
            code = "123456"
        )
    )
}