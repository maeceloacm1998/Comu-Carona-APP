package com.app.comu_carona.components.snackbar

import androidx.compose.ui.graphics.Color
import com.app.comu_carona.theme.BrowDark
import com.app.comu_carona.theme.Error
import com.app.comu_carona.theme.Success

enum class SnackbarCustomType(val background: Color, val text: Color) {
    SUCCESS(Success, Color.White),
    ERROR(Error, Color.White),
    WARNING(BrowDark, Color.White);
}