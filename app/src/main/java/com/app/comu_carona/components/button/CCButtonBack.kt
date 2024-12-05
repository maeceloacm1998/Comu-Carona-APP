package com.app.comu_carona.components.button

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.comu_carona.theme.SoftBlack
import com.app.comu_carona.theme.TextFieldLineColor

@Composable
fun CCButtonBack(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    IconButton(
        modifier = modifier.border(1.dp, TextFieldLineColor, shape = RoundedCornerShape(20)),
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = Color.Transparent,
        )
    ) {
        Icon(
            modifier = Modifier.size(50.dp),
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            contentDescription = "Voltar",
            tint = SoftBlack
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CCButtonBackPreview() {
    CCButtonBack()
}