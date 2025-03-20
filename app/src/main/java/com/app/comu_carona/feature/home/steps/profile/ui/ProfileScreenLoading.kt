package com.app.comu_carona.feature.home.steps.profile.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.comu_carona.components.carridecard.UserSelectionBoxShimmer
import com.app.comu_carona.theme.BackgroundSkeleton
import com.valentinilk.shimmer.shimmer

@Composable
fun ProfileScreenLoading() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {

        Box(
            modifier = Modifier
                .shimmer()
                .padding(vertical = 20.dp, horizontal = 20.dp)
                .size(height = 35.dp, width = 240.dp)
                .background(BackgroundSkeleton)
        )

        Spacer(modifier = Modifier.padding(5.dp))

        UserSelectionBoxShimmer(
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        Spacer(modifier = Modifier.padding(10.dp))

        repeat(2) {
            Box(
                modifier = Modifier
                    .shimmer()
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .height(height = 60.dp)
                    .background(BackgroundSkeleton)
            )
        }
    }
}

@Preview
@Composable
fun ProfileLoadingShimmerPreview() {
    MaterialTheme {
        ProfileScreenLoading()
    }
}
