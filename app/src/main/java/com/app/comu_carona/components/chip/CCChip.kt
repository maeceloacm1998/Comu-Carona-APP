package com.app.comu_carona.components.chip

import androidx.compose.animation.Animatable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.comu_carona.theme.Primary
import com.app.comu_carona.theme.TextFieldColor

@Composable
fun CCChip(
    modifier: Modifier = Modifier,
    title: String,
    isActivated: Boolean = false,
    onClick: () -> Unit = { },
) {
    val borderColorState =  remember { Animatable(TextFieldColor) }
    val backgroundColorState = remember { Animatable(Color.Transparent) }
    val textColorState = remember { Animatable(TextFieldColor) }

    LaunchedEffect(isActivated) {
        borderColorState.animateTo(
            if (isActivated) Primary else TextFieldColor
        )
    }

    LaunchedEffect(isActivated) {
        backgroundColorState.animateTo(
            if (isActivated) Primary else Color.Transparent
        )
    }

    LaunchedEffect(isActivated) {
        textColorState.animateTo(
            if (isActivated) Color.White else TextFieldColor
        )
    }

    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColorState.value,
        ),
        onClick = onClick,
        border = BorderStroke(1.dp, borderColorState.value),
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            color = textColorState.value,
            fontWeight = SemiBold,
            modifier = Modifier.padding(
                start = 5.dp,
                end = 5.dp,
            ),
        )
    }
}

@Preview
@Composable
fun CCChip1Preview() {
    CCChip(
        title = "Todos",
        isActivated = true
    )
}

@Preview(showBackground = true)@Composable
fun CCChip2Preview() {
    Column {
        CCChip(
            title = "Todos",
            isActivated = false
        )

        CCChip(
            title = "Minhas Caronas",
            isActivated = false
        )
    }
}