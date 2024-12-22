package com.app.comu_carona.components.horizontalline

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.app.comu_carona.theme.GrayLine

@Composable
fun HorizontalLine(
    modifier: Modifier = Modifier,
    color: Color = GrayLine,
    thickness: Float = 1f
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(thickness.dp)
            .background(color)
    )
}