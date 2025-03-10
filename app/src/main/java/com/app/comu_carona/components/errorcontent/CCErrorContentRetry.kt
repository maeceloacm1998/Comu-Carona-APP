package com.app.comu_carona.components.errorcontent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.app.comu_carona.R
import com.app.comu_carona.components.button.CCButton
import com.app.comu_carona.theme.TextColor

@Composable
fun CCErrorContentRetry (
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit = {}
) {
    val loadingLottieAnimation by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.search_car_ride)
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LottieAnimation(
            composition = loadingLottieAnimation,
            modifier = Modifier
                .size(120.dp),
            contentScale = ContentScale.Crop,
            iterations = LottieConstants.IterateForever // Makes the animation loop
        )

        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            color = TextColor,
            fontWeight = SemiBold,
            modifier = Modifier.padding(
                horizontal = 20.dp,
                vertical = 30.dp,
            ),
        )

        CCButton(
            modifier = Modifier.padding(vertical = 20.dp, horizontal = 20.dp),
            title = "Tentar novamente",
            onButtonListener = onClick
        )
    }
}


@Preview
@Composable
fun CCErrorContentRetryPreview() {
    CCErrorContentRetry(
        title = "Erro ao carregar a tela, tente novamente"
    )
}