package com.app.comu_carona.feature.home.steps.rideinprogress.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.comu_carona.R
import com.app.comu_carona.components.carridecard.AvailableCarRideCard
import com.app.comu_carona.components.chip.CCChip
import com.app.comu_carona.components.contenterror.CCErrorContent
import com.app.comu_carona.components.horizontalline.HorizontalLine
import com.app.comu_carona.feature.home.steps.rideinprogress.data.models.RideInProgressFilterOptions
import com.app.comu_carona.feature.home.steps.rideinprogress.ui.RideInProgressViewModelEventState.OnNavigateTo
import com.app.comu_carona.feature.home.steps.rideinprogress.ui.RideInProgressViewModelEventState.OnSelectFilter
import com.app.comu_carona.routes.Routes
import com.app.comu_carona.theme.SoftBlack
import com.app.comu_carona.theme.TextFieldLineColor

@Composable
fun RideInProgressScreen(
    uiState: RideInProgressViewModelUiState,
    onEvent: (RideInProgressViewModelEventState) -> Unit
) {
    val filterOptionsWithoutMyRides: List<RideInProgressFilterOptions> =
        RideInProgressFilterOptions.entries.filter { it != RideInProgressFilterOptions.MY_RIDE }

    Scaffold(
        topBar = {
            RideInProgressTopBar(
                modifier = Modifier
                    .background(White)
                    .padding(vertical = 20.dp),
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

            LazyRow {
                item {
                    Spacer(modifier = Modifier.width(15.dp))
                }

                items(filterOptionsWithoutMyRides) { item ->
                    CCChip(
                        modifier = Modifier
                            .padding(vertical = 10.dp, horizontal = 5.dp),
                        title = item.title,
                        isActivated = uiState.rideInProgressFilterSelected == item,
                        onClick = {
                            onEvent(OnSelectFilter(item))
                        }
                    )
                }
            }

            if (uiState is RideInProgressViewModelUiState.HasRiderInProgress) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                ) {
                    items(uiState.rideInProgressList) { items ->
                        AvailableCarRideCard(
                            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                            waitingHour = items.waitingHour,
                            destinationHour = items.destinationHour,
                            waitingAddress = items.waitingAddress,
                            destinationAddress = items.destinationAddress,
                            riderPhotoUrl = items.riderInformation.photoUrl,
                            riderUserName = items.riderInformation.username,
                            riderDescription = "Participa de alvo",
                            status = items.states.map { RideInProgressFilterOptions.fromValue(it) },
                            onClick = {
                                onEvent(
                                    OnNavigateTo(
                                        Routes.RideInProgressDetails.route,
                                        items.uuid
                                    )
                                )
                            }
                        )
                    }
                }
            } else {
                CCErrorContent(
                    modifier = Modifier.fillMaxSize(),
                    title = stringResource(R.string.ride_in_progress_empty_title)
                )
            }
        }
    }
}

@Composable
fun RideInProgressTopBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.ride_in_progress_title),
            style = MaterialTheme.typography.titleMedium,
            color = SoftBlack,
            fontWeight = SemiBold,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
fun RideInProgressScreenPreview() {
    RideInProgressScreen(
        uiState = RideInProgressViewModelUiState.HasRiderInProgress(
            rideInProgressList = listOf(),
            rideInProgressListFiltered = listOf(),
            rideInProgressFilterSelected = RideInProgressFilterOptions.ALL,
            isLoading = false,
            isError = false,
            isRefresh = false,
        ),
        onEvent = {}
    )
}