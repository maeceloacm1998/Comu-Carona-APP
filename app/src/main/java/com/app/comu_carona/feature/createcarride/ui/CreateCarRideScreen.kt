package com.app.comu_carona.feature.createcarride.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.comu_carona.R
import com.app.comu_carona.components.button.CCButton
import com.app.comu_carona.components.button.CCButtonBack
import com.app.comu_carona.components.horizontalline.HorizontalLine
import com.app.comu_carona.components.textfield.CCTextField
import com.app.comu_carona.feature.createcarride.data.models.CreateCarRideSteps
import com.app.comu_carona.feature.createcarride.data.models.CreateCarRideSteps.CAR_QUANTITY_SEATS
import com.app.comu_carona.feature.createcarride.data.models.CreateCarRideSteps.CAR_WAITING_ADDRESS
import com.app.comu_carona.feature.createcarride.ui.CreateCarRideViewModelEventState.OnCarColor
import com.app.comu_carona.feature.createcarride.ui.CreateCarRideViewModelEventState.OnCarModel
import com.app.comu_carona.feature.createcarride.ui.CreateCarRideViewModelEventState.OnCarPlate
import com.app.comu_carona.feature.createcarride.ui.CreateCarRideViewModelEventState.OnNextStep
import com.app.comu_carona.feature.createcarride.ui.CreateCarRideViewModelEventState.OnQuantitySeats
import com.app.comu_carona.feature.createcarride.ui.CreateCarRideViewModelEventState.OnRemoveNewStep
import com.app.comu_carona.theme.Primary
import com.app.comu_carona.theme.SoftBlack
import com.app.comu_carona.theme.TextFieldColor
import com.app.comu_carona.utils.StringUtils.CAR_PLATE_LENGTH

@Composable
fun StageOfCarModelScreen(
    uiState: CreateCarRideViewModelUiState.Steps,
    event: (CreateCarRideViewModelEventState) -> Unit
) {
    val carModelFocusRequester = remember { FocusRequester() }
    val carColorFocusRequester = remember { FocusRequester() }
    val carPlateFocusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        carModelFocusRequester.requestFocus()
        keyboardController?.show()
    }

    Box(Modifier.verticalScroll(rememberScrollState())) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(White)
                .padding(20.dp)
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            CCButtonBack(onClick = {
                // Aviso para voltar para tela inicial
            })

            BackHandler {
                // Aviso para voltar para tela inicial
            }

            Spacer(modifier = Modifier.height(27.dp))

            Text(
                text = stringResource(id = R.string.create_car_ride_car_model_title),
                style = MaterialTheme.typography.titleLarge,
                color = SoftBlack
            )

            Spacer(modifier = Modifier.height(7.dp))

            Text(
                text = stringResource(id = R.string.create_car_ride_car_model_message),
                style = MaterialTheme.typography.bodyLarge,
                color = TextFieldColor
            )

            Spacer(modifier = Modifier.height(30.dp))

            CCTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(carModelFocusRequester),
                placeholder = stringResource(id = R.string.create_car_ride_car_model_hint),
                value = uiState.carModel,
                onValueChange = { text ->
                    event(OnCarModel(text))
                },
                keyboardType = KeyboardType.Text,
                isErrorMessage = false,
                onImeAction = {
                    carPlateFocusRequester.requestFocus()
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            CCTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(carPlateFocusRequester),
                placeholder = stringResource(id = R.string.create_car_ride_car_model_plate_hint),
                value = uiState.carPlate,
                onValueChange = { text ->
                    event(OnCarPlate(text))
                },
                maxLength = CAR_PLATE_LENGTH,
                keyboardType = KeyboardType.Text,
                isErrorMessage = false,
                onImeAction = {
                    carColorFocusRequester.requestFocus()
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            CCTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(carColorFocusRequester),
                placeholder = stringResource(id = R.string.create_car_ride_car_model_color_hint),
                value = uiState.carColor,
                onValueChange = { text ->
                    event(OnCarColor(text))
                },
                keyboardType = KeyboardType.Text,
                isErrorMessage = false,
                onImeAction = {
                    event(OnNextStep(CAR_QUANTITY_SEATS))
                }
            )

            Spacer(modifier = Modifier.height(30.dp))

            CCButton(
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(id = R.string.register_account_stage_of_birth_date_button_title),
                isEnable = uiState.enabledCarModelScreen,
                onButtonListener = {
                    event(OnNextStep(CAR_QUANTITY_SEATS))
                }
            )
        }
    }
}

@Composable
fun StageOfQuantitySeatsScreen(
    uiState: CreateCarRideViewModelUiState.Steps,
    event: (CreateCarRideViewModelEventState) -> Unit
) {
    val minSeats = 1
    val maxSeats = 3

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(20.dp)
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        CCButtonBack(onClick = {
            event(OnRemoveNewStep(CreateCarRideSteps.CAR_MODEL))
        })

        BackHandler {
            event(OnRemoveNewStep(CreateCarRideSteps.CAR_MODEL))
        }

        Spacer(modifier = Modifier.height(27.dp))

        Text(
            text = stringResource(id = R.string.create_car_ride_quantity_seats_title),
            style = MaterialTheme.typography.titleLarge,
            color = SoftBlack
        )

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                modifier = Modifier
                    .border(1.dp, Primary, CircleShape)
                    .size(30.dp),
                onClick = {
                    if (uiState.quantitySeats >= minSeats) {
                        event(OnQuantitySeats(uiState.quantitySeats - 1))
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Remove,
                    contentDescription = "Remove seats",
                    tint = Primary
                )
            }

            Text(
                text = uiState.quantitySeats.toString(),
                style = MaterialTheme.typography.titleLarge,
                fontSize = 60.sp,
                color = Primary
            )

            IconButton(
                modifier = Modifier
                    .border(1.dp, Primary, CircleShape)
                    .size(30.dp),
                onClick = {
                    if (uiState.quantitySeats < maxSeats) {
                        event(OnQuantitySeats(uiState.quantitySeats + 1))
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add seats",
                    tint = Primary
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        CCButton(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.register_account_stage_of_birth_date_button_title),
            isEnable = uiState.quantitySeats > 0,
            onButtonListener = {
                event(OnNextStep(CAR_WAITING_ADDRESS))
            }
        )
    }
}

@Composable
fun StageOfWaitingAddressScreen(
    uiState: CreateCarRideViewModelUiState.Steps,
    title: String,
    textFieldValue: String = "",
    validationNextStep: Boolean = false,
    onValueChange: (String) -> Unit = {},
    onNextAction: () -> Unit = {},
    onBackAction: () -> Unit = {},
    onItemClicked: (address: String) -> Unit = {}
) {
    val focusRequesters = FocusRequester()
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        focusRequesters.requestFocus()
        keyboardController?.show()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        CCButtonBack(onClick = {
            onBackAction()
        })

        BackHandler {
            onBackAction()
        }

        Spacer(modifier = Modifier.height(27.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = SoftBlack
        )

        Spacer(modifier = Modifier.height(30.dp))

        CCTextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequesters),
            placeholder = stringResource(id = R.string.create_car_ride_waiting_address_placeholder),
            value = textFieldValue,
            onValueChange = { text ->
                onValueChange(text)
            },
            keyboardType = KeyboardType.Text,
            isErrorMessage = false,
            onImeAction = {}
        )

        Spacer(modifier = Modifier.height(10.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        ) {
            LazyColumn {
                if (uiState.waitingAddressList.isEmpty()) {
                    item {
                        Text(
                            text = "Nenhum endereço encontrado.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = TextFieldColor
                        )
                    }
                } else {
                    items(uiState.waitingAddressList.size) { index ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp)
                                .clickable {
                                    onItemClicked(uiState.waitingAddressList[index])
                                },
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Image(
                                    modifier = Modifier
                                        .padding(end = 15.dp)
                                        .size(20.dp),
                                    painter = painterResource(id = R.drawable.ic_timer),
                                    contentDescription = "clock",
                                )
                                Text(
                                    text = uiState.waitingAddressList[index],
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = TextFieldColor
                                )
                            }

                            Icon(
                                imageVector = Icons.Filled.ArrowForward,
                                contentDescription = "Add address",
                                tint = TextFieldColor
                            )
                        }
                        HorizontalLine(modifier = Modifier.padding(vertical = 10.dp))
                    }
                }
            }
        }

        HorizontalLine(modifier = Modifier.padding(vertical = 20.dp))

        CCButton(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.register_account_stage_of_birth_date_button_title),
            isEnable = validationNextStep,
            onButtonListener = {
                onNextAction()
            }
        )
    }
}

@Composable
fun StageOfWaitingHourScreen(
    uiState: CreateCarRideViewModelUiState.Steps,
    title: String,
    onValueChange: (String) -> Unit = {},
    onNextAction: () -> Unit = {},
    onBackAction: () -> Unit = {},
) {
    val firstTextFieldFocus = FocusRequester()
    val secondTextFieldFocus = FocusRequester()
    val keyboardController = LocalSoftwareKeyboardController.current

    var firstTextField by remember { mutableStateOf("") }
    var secondTextField by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        firstTextFieldFocus.requestFocus()
        keyboardController?.show()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(20.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        CCButtonBack(onClick = {
            onBackAction()
        })

        BackHandler {
            onBackAction()
        }

        Spacer(modifier = Modifier.height(27.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = SoftBlack
        )

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CCTextField(
                modifier = Modifier
                    .weight(0.5f)
                    .height(150.dp)
                    .focusRequester(firstTextFieldFocus),
                placeholder = "00",
                placeholderSize = 60,
                textAlign = TextAlign.Center,
                maxLength = 2,
                value = firstTextField,
                onValueChange = { text ->
                    if (text.length == 2) {
                        secondTextFieldFocus.requestFocus()
                    }
                    firstTextField = text
                },
                keyboardType = KeyboardType.Text,
                isErrorMessage = false,
                onImeAction = {}
            )

            Text(
                modifier = Modifier.padding(horizontal = 10.dp),
                text = ":",
                style = MaterialTheme.typography.titleLarge,
                fontSize = 60.sp,
                color = TextFieldColor
            )

            CCTextField(
                modifier = Modifier
                    .height(150.dp)
                    .weight(0.5f)
                    .focusRequester(secondTextFieldFocus),
                placeholder = "00",
                placeholderSize = 60,
                textAlign = TextAlign.Center,
                maxLength = 2,
                value = secondTextField,
                onValueChange = { text ->
                    if (text.isEmpty()) {
                        firstTextFieldFocus.requestFocus()
                    }
                    secondTextField = text
                },
                keyboardType = KeyboardType.Text,
                isErrorMessage = false,
                onImeAction = {
                    onValueChange("${firstTextField}:${secondTextField}")
                    onNextAction()
                }
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        CCButton(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.register_account_stage_of_birth_date_button_title),
            isEnable = (firstTextField + secondTextField).length == 4,
            isLoading = uiState.isLoading,
            isSuccess = uiState.isSuccess,
            onButtonListener = {
                onValueChange("${firstTextField}:${secondTextField}")
                onNextAction()
            }
        )
    }
}


@Preview
@Composable
fun StageOfCarModelScreenPreview() {
    StageOfCarModelScreen(
        uiState = CreateCarRideViewModelUiState.Steps(
            steps = CreateCarRideSteps.CAR_MODEL,
            carModel = "",
            carColor = "",
            carPlate = "",
            quantitySeats = 0,
            waitingAddress = "",
            waitingAddressList = emptyList(),
            destinationAddress = "",
            destinationAddressList = emptyList(),
            waitingHour = "",
            destinationHour = "",
            enabledCarModelScreen = false,
            isLoading = false,
            isError = false,
            isSuccess = false,
        ),
        event = {}
    )
}

@Preview
@Composable
fun StageOfQuantitySeatsScreenPreview() {
    StageOfQuantitySeatsScreen(
        uiState = CreateCarRideViewModelUiState.Steps(
            steps = CAR_QUANTITY_SEATS,
            carModel = "",
            carColor = "",
            carPlate = "",
            quantitySeats = 0,
            waitingAddress = "",
            waitingAddressList = emptyList(),
            destinationAddress = "",
            destinationAddressList = emptyList(),
            waitingHour = "",
            destinationHour = "",
            enabledCarModelScreen = false,
            isLoading = false,
            isError = false,
            isSuccess = false,
        ),
        event = {}
    )
}

@Preview
@Composable
fun StageOfWaitingAddressScreenPreview() {
    StageOfWaitingAddressScreen(
        uiState = CreateCarRideViewModelUiState.Steps(
            steps = CAR_WAITING_ADDRESS,
            carModel = "",
            carColor = "",
            carPlate = "",
            quantitySeats = 0,
            waitingAddress = "",
            waitingAddressList = emptyList(),
            destinationAddress = "",
            destinationAddressList = emptyList(),
            waitingHour = "",
            destinationHour = "",
            enabledCarModelScreen = false,
            isLoading = false,
            isError = false,
            isSuccess = false,
        ),
        title = "Waiting Address",
        onNextAction = {}
    )
}

@Preview
@Composable
fun StageOfWaitingHourScreenPreview() {
    StageOfWaitingHourScreen(
        uiState = CreateCarRideViewModelUiState.Steps(
            steps = CreateCarRideSteps.CAR_WAITING_HOUR,
            carModel = "",
            carColor = "",
            carPlate = "",
            quantitySeats = 0,
            waitingAddress = "",
            waitingAddressList = emptyList(),
            destinationAddress = "",
            destinationAddressList = emptyList(),
            waitingHour = "",
            destinationHour = "",
            enabledCarModelScreen = false,
            isLoading = false,
            isError = false,
            isSuccess = false,
        ),
        title = "Waiting Hour",
    )
}