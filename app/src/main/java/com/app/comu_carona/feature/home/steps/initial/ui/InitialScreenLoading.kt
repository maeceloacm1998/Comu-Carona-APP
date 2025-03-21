package com.app.comu_carona.feature.home.steps.initial.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.comu_carona.R
import com.app.comu_carona.components.carridecard.AvailableCarRideCardLoading
import com.app.comu_carona.components.horizontalline.HorizontalLine
import com.app.comu_carona.theme.SoftBlack

@Composable
fun InitialScreenLoading(
    userName: String,
    photoUrl: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        InitialTopBar(
            modifier = Modifier
                .padding(vertical = 20.dp),
            userName = userName,
            photoUrl = photoUrl
        )
        HorizontalLine()
        AvailableCarRideCardLoading(
            modifier = Modifier.padding(vertical = 30.dp, horizontal = 20.dp)
        )
        Text(
            text = stringResource(R.string.initial_available_car_ride_title),
            style = MaterialTheme.typography.titleSmall,
            color = SoftBlack,
            fontWeight = SemiBold,
            modifier = Modifier.padding(
                start = 20.dp,
                end = 20.dp,
                bottom = 20.dp
            ),
        )
        Column(
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            AvailableCarRideCardLoading(
                modifier = Modifier.padding(bottom = 10.dp)
            )
            AvailableCarRideCardLoading(
                modifier = Modifier.padding(bottom = 10.dp)
            )
            AvailableCarRideCardLoading(
                modifier = Modifier.padding(bottom = 10.dp)
            )
            AvailableCarRideCardLoading(
                modifier = Modifier.padding(bottom = 10.dp)
            )
            AvailableCarRideCardLoading(
                modifier = Modifier.padding(bottom = 10.dp)
            )
        }

    }
}

@Preview
@Composable
fun InitialScreenLoadingPreview() {
    InitialScreenLoading(
        userName = "User Name",
        photoUrl = ""
    )
}