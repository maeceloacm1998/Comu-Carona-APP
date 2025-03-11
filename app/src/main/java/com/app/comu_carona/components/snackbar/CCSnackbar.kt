package com.app.comu_carona.components.snackbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDefaults
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * A custom Snackbar component.
 *
 * @param modifier Modifier to be applied to the Snackbar.
 * @param snackbarHostState State of the Snackbar host.
 * @param snackbarType Type of the Snackbar to determine its appearance.
 */
@Composable
fun CCSnackbar(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
    snackbarType: SnackbarCustomType = SnackbarCustomType.SUCCESS,
) {
    Box(modifier = modifier.padding(16.dp)) {
        SnackbarHost(
            hostState = snackbarHostState,
            snackbar = { data ->
                Snackbar(
                    containerColor = snackbarType.background,
                    contentColor = SnackbarDefaults.contentColor,
                    shape = SnackbarDefaults.shape,
                    actionOnNewLine = false,
                    snackbarData = data
                )
            }
        )
    }
}