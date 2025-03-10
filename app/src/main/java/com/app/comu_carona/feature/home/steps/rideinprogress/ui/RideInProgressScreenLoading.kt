package com.app.comu_carona.feature.home.steps.rideinprogress.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.comu_carona.components.carridecard.AvailableCarRideCardLoading
import com.app.comu_carona.components.tag.CCTagLoading
import com.app.comu_carona.theme.BackgroundSkeleton
import com.valentinilk.shimmer.shimmer

@Composable
fun RideInProgressScreenLoading() {
    val tagSpacing = Modifier
        .padding(vertical = 10.dp, horizontal = 5.dp)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {

        Box(
            modifier = Modifier
                .shimmer()
                .padding(vertical = 20.dp)
                .size(height = 35.dp, width = 240.dp)
                .background(BackgroundSkeleton)
        )

        Row {
            CCTagLoading(
                modifier = tagSpacing
            )
            CCTagLoading(
                modifier = tagSpacing
            )
            CCTagLoading(
                modifier = tagSpacing
            )
            CCTagLoading(
                modifier = tagSpacing
            )
        }

        Column {
            AvailableCarRideCardLoading(
                modifier = Modifier.padding(
                    top = 30.dp,
                    start = 20.dp,
                    end = 20.dp
                )
            )
            AvailableCarRideCardLoading(
                modifier = Modifier.padding(
                    top = 15.dp,
                    start = 20.dp,
                    end = 20.dp
                )
            )
            AvailableCarRideCardLoading(
                modifier = Modifier.padding(
                    top = 15.dp,
                    start = 20.dp,
                    end = 20.dp
                )
            )
            AvailableCarRideCardLoading(
                modifier = Modifier.padding(
                    top = 15.dp,
                    start = 20.dp,
                    end = 20.dp
                )
            )
        }
    }
}

@Preview
@Composable
fun RideInProgressScreenLoadingPreview() {
    RideInProgressScreenLoading()
}