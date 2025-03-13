package com.app.comu_carona.feature.carridedetails.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.comu_carona.R
import com.app.comu_carona.components.button.CCButton
import com.app.comu_carona.components.button.CCButtonBack
import com.app.comu_carona.components.carridecard.AddressBoxShimmer
import com.app.comu_carona.components.carridecard.UserSelectionBoxShimmer
import com.app.comu_carona.components.horizontalline.HorizontalLine
import com.app.comu_carona.theme.BackgroundSkeleton
import com.valentinilk.shimmer.shimmer

@Composable
fun CarRideDetailsScreenLoading(
    onBack: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier.weight(2f)
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            CCButtonBack(onClick = onBack)

            Spacer(modifier = Modifier.height(35.dp))

            Column(
                modifier = Modifier
                    .shimmer()
            ) {
                Box(
                    modifier = Modifier
                        .size(height = 30.dp, width = 200.dp)
                        .background(BackgroundSkeleton)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Box(
                    modifier = Modifier
                        .size(height = 30.dp, width = 120.dp)
                        .background(BackgroundSkeleton)
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            AddressBoxShimmer()

            HorizontalLine(modifier = Modifier.padding(vertical = 20.dp))

            UserSelectionBoxShimmer()
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(White)
        ) {
            HorizontalLine(Modifier.padding(bottom = 20.dp))

            CCButton(
                Modifier.padding(bottom = 20.dp),
                title = stringResource(R.string.car_ride_details_reservation_button_title),
                isEnable = false,
            )
        }
    }
}

@Preview
@Composable
fun CarRideDetailsScreenLoadingPreview() {
    CarRideDetailsScreenLoading()
}