package com.app.comu_carona.feature.checkcode

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.app.comu_carona.components.button.CCButton

@Composable
fun CheckCodeRoute() {
    Column {
        CCButton(
            title = "Check Code",
            isSuccess = true,
        )
    }
}