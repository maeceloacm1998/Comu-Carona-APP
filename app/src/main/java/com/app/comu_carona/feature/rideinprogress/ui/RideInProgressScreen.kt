package com.app.comu_carona.feature.rideinprogress.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import com.app.comu_carona.components.horizontalline.HorizontalLine
import com.app.comu_carona.feature.rideinprogress.data.models.RideInProgressFilterOptions
import com.app.comu_carona.theme.SoftBlack
import com.app.comu_carona.theme.TextFieldLineColor

@Composable
fun RideInProgressScreen(
    uiState: RideInProgressViewModelUiState.HasRiderInProgress
) {
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
                items(RideInProgressFilterOptions.entries.size) { index ->
                    CCChip(
                        modifier = Modifier
                            .padding(vertical = 10.dp, horizontal = 5.dp),
                        title = RideInProgressFilterOptions.entries[index].value,
                    )
                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                items(uiState.rideInProgressList.size) { index ->
                    AvailableCarRideCard(
                        availableCarRide = uiState.rideInProgressList[index],
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                    )
                }
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
            isLoading = false,
            isError = false,
            isRefresh = false,
            isSuccess = false,
        )
    )
}