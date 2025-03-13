package com.app.comu_carona.feature.carridedetails.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.comu_carona.R
import com.app.comu_carona.components.bottomsheet.CCBottomSheet
import com.app.comu_carona.components.button.CCButton
import com.app.comu_carona.components.button.CCButtonBack
import com.app.comu_carona.components.button.CCOutlinedButton
import com.app.comu_carona.components.carridecard.AddressBox
import com.app.comu_carona.components.carridecard.UserSelectionBox
import com.app.comu_carona.components.horizontalline.HorizontalLine
import com.app.comu_carona.components.snackbar.CCSnackbar
import com.app.comu_carona.components.snackbar.SnackbarCustomType
import com.app.comu_carona.feature.carridedetails.data.models.BottomSheetCarRideUser
import com.app.comu_carona.feature.carridedetails.ui.CarRideDetailsViewModelEventState.OnBack
import com.app.comu_carona.feature.carridedetails.ui.CarRideDetailsViewModelEventState.OnOpenBottomSheet
import com.app.comu_carona.feature.carridedetails.ui.CarRideDetailsViewModelEventState.OnReservationRide
import com.app.comu_carona.theme.SoftBlack
import com.app.comu_carona.theme.Success
import com.app.comu_carona.theme.TextFieldLightColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarRideDetailsScreen(
    uiState: CarRideDetailsViewModelUiState.HasCarRideDetails,
    onEvent: (CarRideDetailsViewModelEventState) -> Unit,
    snackbarHostState: SnackbarHostState
) {
    Scaffold(
        snackbarHost = {
            CCSnackbar(
                snackbarHostState = snackbarHostState,
                snackbarType = uiState.snackbarType
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .weight(2f)
                    .verticalScroll(rememberScrollState())
                    .background(White)
                    .padding(20.dp)
            ) {

                Spacer(modifier = Modifier.height(20.dp))

                CCButtonBack(onClick = {
                    onEvent(OnBack)
                })

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = uiState.carRideDetailsResponse?.dateTitle
                        ?: stringResource(R.string.car_ride_details_date_title_empty),
                    style = MaterialTheme.typography.titleLarge,
                    color = SoftBlack,
                    modifier = Modifier.fillMaxWidth(),
                )

                Spacer(modifier = Modifier.height(30.dp))

                AddressBox(
                    waitingHour = uiState.carRideDetailsResponse?.waitingHour
                        ?: stringResource(R.string.car_ride_details_hour_title_empty),
                    destinationHour = uiState.carRideDetailsResponse?.destinationHour
                        ?: stringResource(R.string.car_ride_details_hour_title_empty),
                    waitingAddress = uiState.carRideDetailsResponse?.destinationHour
                        ?: stringResource(R.string.car_ride_details_address_title_empty),
                    destinationAddress = uiState.carRideDetailsResponse?.destinationHour
                        ?: stringResource(R.string.car_ride_details_address_title_empty)
                )

                HorizontalLine(modifier = Modifier.padding(vertical = 25.dp))

                UserSelectionBox(
                    modifier = Modifier.clickable {
                        onEvent(OnOpenBottomSheet)
                    },
                    riderPhotoUrl = uiState.carRideDetailsResponse?.riderPhoto ?: "",
                    riderUserName = uiState.carRideDetailsResponse?.riderUsername
                        ?: stringResource(R.string.car_ride_details_username_title_empty),
                    riderDescription = uiState.carRideDetailsResponse?.riderDescription
                        ?: stringResource(
                            R.string.car_ride_details_description_title_empty
                        ),
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = uiState.carRideDetailsResponse?.description
                        ?: stringResource(R.string.car_ride_details_description_title_empty),
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = Bold,
                    color = TextFieldLightColor,
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(White)
                    .padding(horizontal = 20.dp)
            ) {
                HorizontalLine(Modifier.padding(bottom = 20.dp))

                CCButton(
                    Modifier.padding(bottom = 20.dp),
                    title = stringResource(R.string.car_ride_details_reservation_button_title),
                    onButtonListener = { onEvent(OnReservationRide) }
                )
            }
        }

        // Contact bottom sheet
        CCBottomSheet(
            showSheet = uiState.showBottomSheet,
            onDismissRequest = {
                onEvent(CarRideDetailsViewModelEventState.OnDismissBottomSheet)
            },
            containerColor = White
        ) {
            if (uiState.carRideDetailsResponse != null) {
                UserDetailsBottomSheet(uiState.carRideDetailsResponse.bottomSheetCarRideUser)
            }
        }
    }
}

@Composable
fun UserDetailsBottomSheet(data: BottomSheetCarRideUser) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(White)
            .padding(horizontal = 24.dp, vertical = 20.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.car_ride_details_user_details_title),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = Bold,
            color = SoftBlack,
        )

        Spacer(modifier = Modifier.height(30.dp))

        UserSelectionBox(
            riderPhotoUrl = data.bottomSheetRiderPhoto,
            riderUserName = data.bottomSheetRiderUsername,
            riderDescription = data.bottomSheetRiderDescription,
            showArrow = false
        )

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CCOutlinedButton(
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
                    .padding(end = 7.dp),
                title = stringResource(R.string.car_ride_details_user_details_wpp_title),
                titleColor = Success,
                borderColor = Success,
                icon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_wpp),
                        contentDescription = stringResource(R.string.car_ride_details_user_details_wpp_title),
                        modifier = Modifier.padding(end = 5.dp)
                    )
                }
            )
            CCOutlinedButton(
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
                    .padding(start = 7.dp),
                title = stringResource(R.string.car_ride_details_user_details_phone_title),
                icon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_phone),
                        contentDescription = stringResource(R.string.car_ride_details_user_details_phone_title),
                        modifier = Modifier.padding(end = 5.dp)
                    )
                }
            )
        }
    }
}

@Preview
@Composable
fun UserDetailsBottomSheetPreview() {
    UserDetailsBottomSheet(
        data = BottomSheetCarRideUser(
            bottomSheetRiderPlate = "ABC-1234",
            bottomSheetRiderUsername = "John Doe",
            bottomSheetRiderDescription = "Lorem ipsum dolor sit amet",
            bottomSheetRiderPhoto = "",
            bottomSheetCarRiderDescription = "Lorem ipsum dolor sit amet",
            bottomSheetRiderPhoneNumber = "123456789"
        )
    )
}

@Preview
@Composable
fun CarRideDetailsScreenPreview() {
    CarRideDetailsScreen(
        uiState = CarRideDetailsViewModelUiState.HasCarRideDetails(
            carRideDetailsResponse = null,
            snackbarType = SnackbarCustomType.SUCCESS,
            showSnackBar = false,
            showBottomSheet = false,
            isSuccessReservation = false,
            isLoading = false,
            isError = false,
            isLoadingReservation = false
        ),
        onEvent = {},
        snackbarHostState = SnackbarHostState()
    )
}