package com.app.comu_carona.components.tag

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.comu_carona.theme.BackgroundSkeleton
import com.app.comu_carona.theme.TextFieldColor
import com.valentinilk.shimmer.shimmer

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

@Composable
fun CCTagLoading(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .shimmer()
            .size(height = 35.dp, width = 120.dp)
            .background(
                BackgroundSkeleton,
                RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 15.dp, vertical = 4.dp)
    )
}

@Preview
@Composable
fun CCTagPreview() {
    CCTag(title = "Minha Carona", color = TextFieldColor)
}

@Preview
@Composable
fun CCTagLoadingPreview() {
    CCTagLoading()
}