package com.app.comu_carona.components.tag

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.comu_carona.theme.TextFieldColor

@Composable
fun CCTag(
    modifier: Modifier = Modifier,
    title: String,
    color: Color
) {
    Column(
        modifier = modifier
            .background(color, RoundedCornerShape(10.dp))
            .padding(horizontal = 15.dp, vertical = 4.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.displaySmall,
            color = Color.White,
            fontWeight = SemiBold,
        )
    }
}

@Preview
@Composable
fun CCTagPreview() {
    CCTag(title = "Minha Carona", color = TextFieldColor)
}