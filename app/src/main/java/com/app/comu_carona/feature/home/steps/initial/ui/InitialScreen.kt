package com.app.comu_carona.feature.home.steps.initial.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale.Companion.FillBounds
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.app.comu_carona.R
import com.app.comu_carona.components.carridecard.AvailableCarRideCard
import com.app.comu_carona.components.errorcontent.CCErrorContent
import com.app.comu_carona.components.horizontalline.HorizontalLine
import com.app.comu_carona.components.shimmerimage.CCShimmerImage
import com.app.comu_carona.feature.home.steps.initial.data.models.AvailableCarRide
import com.app.comu_carona.feature.home.steps.initial.ui.InitialViewModelEventState.OnNavigateTo
import com.app.comu_carona.routes.Routes
import com.app.comu_carona.theme.SoftBlack
import com.app.comu_carona.theme.TextFieldLineColor

@Composable
fun InitialScreen(
    uiState: InitialViewModelUiState.HasAvailableCarRide,
    onEvent: (InitialViewModelEventState) -> Unit
) {
    Scaffold(
        topBar = {
            InitialTopBar(
                modifier = Modifier
                    .background(White)
                    .padding(vertical = 20.dp),
                userName = uiState.userName,
                photoUrl = uiState.photoUrl
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(White)
        ) {
            HorizontalLine(
                modifier = Modifier
                    .padding(
                        horizontal = 10.dp,
                    ),
                color = TextFieldLineColor,
                thickness = 1f
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                // Create Car Drive Banner
                item {
                    BannerCreateCarRide(onEvent)
                }

                if (uiState.availableCarRideList.isNotEmpty()) {
                    // Available Car Rides Title
                    item {
                        Text(
                            text = stringResource(R.string.initial_available_car_ride_title),
                            style = MaterialTheme.typography.titleSmall,
                            color = SoftBlack,
                            fontWeight = SemiBold,
                            modifier = Modifier.padding(
                                start = 20.dp,
                                end = 20.dp,
                            ),
                        )
                    }

                    items(uiState.availableCarRideList) { availableCarRide ->
                        AvailableCarRideCard(
                            waitingHour = availableCarRide.waitingHour,
                            destinationHour = availableCarRide.destinationHour,
                            waitingAddress = availableCarRide.waitingAddress,
                            destinationAddress = availableCarRide.destinationAddress,
                            riderPhotoUrl = availableCarRide.riderPhotoUrl,
                            riderUserName = availableCarRide.riderUserName,
                            riderDescription = availableCarRide.riderDescription,
                            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                        )
                    }
                } else {
                    item {
                        CCErrorContent(
                            title = stringResource(R.string.initial_not_available_car_ride_title)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun InitialTopBar(
    modifier: Modifier = Modifier,
    userName: String,
    photoUrl: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.initial_username_title, userName),
            style = MaterialTheme.typography.titleMedium,
            color = SoftBlack,
            fontWeight = SemiBold,
            textAlign = TextAlign.Center,
        )
        CCShimmerImage(
            imageUrl = photoUrl,
            contentScale = FillBounds,
            imageSize = 40
        )
    }
}

@Composable
fun BannerCreateCarRide(
    onEvent: (InitialViewModelEventState) -> Unit
) {
    Image(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 30.dp)
            .fillMaxWidth()
            .height(135.dp)
            .clickable {
                onEvent(OnNavigateTo(Routes.CreateCarRide.route))
            },
        painter = painterResource(R.drawable.ic_create_car_ride),
        contentDescription = "",
        contentScale = FillBounds,
    )
}

@Preview
@Composable
fun InitialScreenPreview() {
    InitialScreen(
        uiState = InitialViewModelUiState.HasAvailableCarRide(
            availableCarRideList = listOf(
                AvailableCarRide(
                    id = "1",
                    waitingHour = "10:00",
                    destinationHour = "11:00",
                    waitingAddress = "Rua Teste, 123",
                    destinationAddress = "Rua Teste, 456",
                    riderPhotoUrl = "https://firebasestorage.googleapis.com/v0/b/comu-carona.firebasestorage.app/o/uploads%2Fupload2233688854578175299.tmp?alt=media&token=486be14c-5708-4c8d-a1e6-650867480c0a",
                    riderUserName = "Teste",
                    riderDescription = "Descrição"
                )
            ),
            userName = "Teste",
            photoUrl = "https://firebasestorage.googleapis.com/v0/b/comu-carona.firebasestorage.app/o/uploads%2Fupload2233688854578175299.tmp?alt=media&token=486be14c-5708-4c8d-a1e6-650867480c0a",
            isLoading = false,
            isError = false,
            isRefresh = false,
            isSuccess = true
        ),
        onEvent = {},
    )
}