package com.app.comu_carona.feature.myrideinprogressdetails.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.comu_carona.R
import com.app.comu_carona.components.bottomsheet.CCBottomSheet
import com.app.comu_carona.components.button.CCButton
import com.app.comu_carona.components.button.CCButtonBack
import com.app.comu_carona.components.carridecard.AddressBox
import com.app.comu_carona.components.carridecard.UserSelectionBox
import com.app.comu_carona.components.horizontalline.HorizontalLine
import com.app.comu_carona.components.snackbar.CCSnackbar
import com.app.comu_carona.components.snackbar.SnackbarCustomType
import com.app.comu_carona.feature.carridedetails.data.models.BottomSheetCarRideUser
import com.app.comu_carona.feature.carridedetails.ui.UserDetailsBottomSheet
import com.app.comu_carona.feature.myrideinprogressdetails.ui.MyRideInProgressDetailsViewModelEventState.OnCallPhone
import com.app.comu_carona.feature.myrideinprogressdetails.ui.MyRideInProgressDetailsViewModelEventState.OnCallWhatsApp
import com.app.comu_carona.feature.myrideinprogressdetails.ui.MyRideInProgressDetailsViewModelEventState.OnOpenBottomSheet
import com.app.comu_carona.feature.myrideinprogressdetails.ui.MyRideInProgressDetailsViewModelEventState.OnOpenShare
import com.app.comu_carona.feature.myrideinprogressdetails.ui.MyRideInProgressDetailsViewModelEventState.OnCancelMyRide
import com.app.comu_carona.feature.myrideinprogressdetails.ui.MyRideInProgressDetailsViewModelEventState.OnDismissBottomSheet
import com.app.comu_carona.feature.myrideinprogressdetails.ui.MyRideInProgressDetailsViewModelEventState.OnOpenCancelBottomSheet
import com.app.comu_carona.theme.Error
import com.app.comu_carona.theme.SoftBlack
import com.app.comu_carona.theme.TextFieldColor
import com.app.comu_carona.theme.TextFieldLightColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyRideInProgressDetailsScreen(
    uiState: MyRideInProgressDetailsViewModelUiState.HasCarRideDetails,
    onEvent: (MyRideInProgressDetailsViewModelEventState) -> Unit,
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

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CCButtonBack(onClick = {
                        onEvent(MyRideInProgressDetailsViewModelEventState.OnBack)
                    })

                    IconButton(
                        onClick = { onEvent(OnOpenShare) }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = null,
                            tint = SoftBlack
                        )
                    }
                }

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
                    waitingAddress = uiState.carRideDetailsResponse?.waitingAddress
                        ?: stringResource(R.string.car_ride_details_address_title_empty),
                    destinationAddress = uiState.carRideDetailsResponse?.destinationAddress
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
                    text = stringResource(R.string.my_ride_in_progress_details_reservations_title),
                    style = MaterialTheme.typography.titleSmall,
                    color = SoftBlack,
                    fontWeight = Bold,
                    modifier = Modifier.fillMaxWidth().padding(bottom = 25.dp),
                )

                if(uiState.carRideDetailsResponse?.reservations.isNullOrEmpty()) {
                    Text(
                        text = stringResource(R.string.my_ride_in_progress_details_reservations_empty_title),
                        style = MaterialTheme.typography.bodySmall,
                        color = TextFieldLightColor,
                        modifier = Modifier.fillMaxWidth().padding(bottom = 25.dp),
                    )
                } else {
                    LazyColumn {
                        items(uiState.carRideDetailsResponse?.reservations ?: emptyList()) { reservation ->
                            Text(
                                text = reservation.username,
                                style = MaterialTheme.typography.bodySmall,
                                color = TextFieldLightColor,
                                modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
                            )
                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(White)
                    .padding(horizontal = 20.dp)
            ) {
                HorizontalLine(Modifier.padding(bottom = 10.dp))

                CCButton(
                    Modifier.padding(bottom = 20.dp),
                    title = stringResource(R.string.my_ride_in_progress_details_cancel_button_title),
                    isLoading = uiState.isLoadingReservation,
                    isSuccess = uiState.isSuccessReservation,
                    isEnable = uiState.isEnableButton,
                    titleColor = Error,
                    containerColor = Transparent,
                    onButtonListener = { onEvent(OnOpenCancelBottomSheet) }
                )
            }
        }

        // Contact bottom sheet
        CCBottomSheet(
            showSheet = uiState.showBottomSheet,
            onDismissRequest = {
                onEvent(OnDismissBottomSheet)
            },
            containerColor = White,
        ) {
            if (uiState.carRideDetailsResponse != null) {
                UserDetailsBottomSheet(
                    data = uiState.carRideDetailsResponse.bottomSheetCarRideUser,
                    onClickWhatsapp = {
                        onEvent(OnCallWhatsApp)
                    },
                    onClickPhone = {
                        onEvent(OnCallPhone)
                    }
                )
            }
        }

        // Cancel Bottom Sheet
        CCBottomSheet(
            showSheet = uiState.showCancelBottomSheet,
            onDismissRequest = {
                onEvent(OnDismissBottomSheet)
            },
            containerColor = White,
        ) {
            CancelBottomSheet(
                onDismissBottomSheet = { onEvent(OnDismissBottomSheet) },
                onConfirmCancel = { onEvent(OnCancelMyRide) }
            )
        }
    }
}

@Composable
fun CancelBottomSheet(
    onDismissBottomSheet: () -> Unit = {},
    onConfirmCancel: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(White)
            .padding(20.dp)
    ) {
        Text(
            text = stringResource(R.string.my_ride_in_progress_details_cancel_bottom_sheet_title),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            color = SoftBlack,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(R.string.my_ride_in_progress_details_cancel_bottom_sheet_message),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall,
            color = TextFieldColor,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(25.dp))

        CCButton(
            title = stringResource(R.string.my_ride_in_progress_details_cancel_bottom_sheet_cancel_button_title),
            onButtonListener = onDismissBottomSheet
        )

        CCButton(
            title = stringResource(R.string.my_ride_in_progress_details_cancel_bottom_sheet_confirm_button_title),
            titleColor = Error,
            containerColor = Transparent,
            onButtonListener = onConfirmCancel
        )
    }
}

@Preview
@Composable
fun CancelBottomSheetPreview() {
    CancelBottomSheet(
        onDismissBottomSheet = {},
        onConfirmCancel = {}
    )
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
        ),
        onClickWhatsapp = {},
        onClickPhone = {}
    )
}

@Preview
@Composable
fun CarRideDetailsScreenPreview() {
    MyRideInProgressDetailsScreen(
        uiState = MyRideInProgressDetailsViewModelUiState.HasCarRideDetails(
            carRideDetailsResponse = null,
            snackbarType = SnackbarCustomType.SUCCESS,
            showSnackBar = false,
            showBottomSheet = false,
            showCancelBottomSheet = false,
            isSuccessReservation = false,
            isLoading = false,
            isError = false,
            isLoadingReservation = false,
            isEnableButton = true
        ),
        onEvent = {},
        snackbarHostState = SnackbarHostState()
    )
}