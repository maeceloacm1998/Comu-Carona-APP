package com.app.comu_carona.feature.createcarride.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.app.comu_carona.R
import com.app.comu_carona.feature.createcarride.data.models.CreateCarRideSteps.CAR_DESTINATION_ADDRESS
import com.app.comu_carona.feature.createcarride.data.models.CreateCarRideSteps.CAR_DESTINATION_HOUR
import com.app.comu_carona.feature.createcarride.data.models.CreateCarRideSteps.CAR_MODEL
import com.app.comu_carona.feature.createcarride.data.models.CreateCarRideSteps.CAR_QUANTITY_SEATS
import com.app.comu_carona.feature.createcarride.data.models.CreateCarRideSteps.CAR_WAITING_ADDRESS
import com.app.comu_carona.feature.createcarride.data.models.CreateCarRideSteps.CAR_WAITING_HOUR
import com.app.comu_carona.feature.createcarride.data.models.CreateCarRideSteps.FINISH
import com.app.comu_carona.feature.createcarride.data.models.CreateCarRideSteps.WAITING_CREATE_RIDE
import com.app.comu_carona.feature.createcarride.ui.CreateCarRideViewModelEventState.OnClearAddressList
import com.app.comu_carona.feature.createcarride.ui.CreateCarRideViewModelEventState.OnDestinationAddress
import com.app.comu_carona.feature.createcarride.ui.CreateCarRideViewModelEventState.OnDestinationHour
import com.app.comu_carona.feature.createcarride.ui.CreateCarRideViewModelEventState.OnNextStep
import com.app.comu_carona.feature.createcarride.ui.CreateCarRideViewModelEventState.OnRemoveNewStep
import com.app.comu_carona.feature.createcarride.ui.CreateCarRideViewModelEventState.OnWaitingAddress
import com.app.comu_carona.feature.createcarride.ui.CreateCarRideViewModelEventState.OnWaitingHour
import com.app.comu_carona.utils.AnimatedUtils.animatedTransitionPage
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun CreateCarRideRoute(
    navController: NavController
) {
    val createCarRideViewModel: CreateCarRideViewModel = koinViewModel(
        parameters = {
            parametersOf(navController)
        }
    )
    val uiState by createCarRideViewModel.uiState.collectAsStateWithLifecycle()

    CreateCarRideRoute(
        uiState = uiState,
        event = createCarRideViewModel::onEvent
    )
}

@Composable
fun CreateCarRideRoute(
    uiState: CreateCarRideViewModelUiState,
    event: (CreateCarRideViewModelEventState) -> Unit
) {
    check(uiState is CreateCarRideViewModelUiState.Steps)

    Column {
        AnimatedContent(
            modifier = Modifier.weight(2f),
            targetState = uiState.steps,
            label = "AnimatedContent",
            transitionSpec = animatedTransitionPage()
        ) { targetState ->
            when (targetState) {
                CAR_MODEL -> StageOfCarModelScreen(
                    uiState = uiState,
                    event = event
                )

                CAR_QUANTITY_SEATS -> StageOfQuantitySeatsScreen(
                    uiState = uiState,
                    event = event
                )

                CAR_WAITING_ADDRESS -> StageOfWaitingAddressScreen(
                    uiState = uiState,
                    title = stringResource(id = R.string.create_car_ride_waiting_address_title),
                    textFieldValue = uiState.waitingAddress,
                    validationNextStep = uiState.waitingAddress.isNotEmpty(),
                    onValueChange = { event(OnWaitingAddress(it)) },
                    onNextAction = {
                        event(OnClearAddressList)
                        event(OnNextStep(CAR_WAITING_HOUR))
                    },
                    onBackAction = { event(OnRemoveNewStep(CAR_QUANTITY_SEATS)) },
                    onItemClicked = { event(OnWaitingAddress(it)) }
                )

                CAR_WAITING_HOUR -> StageOfWaitingHourScreen(
                    uiState = uiState,
                    title = stringResource(id = R.string.create_car_ride_waiting_hour_title),
                    onValueChange = { event(OnWaitingHour(it)) },
                    onNextAction = { event(OnNextStep(CAR_DESTINATION_ADDRESS)) },
                    onBackAction = { event(OnRemoveNewStep(CAR_WAITING_ADDRESS)) }
                )

                CAR_DESTINATION_ADDRESS -> StageOfWaitingAddressScreen(
                    uiState = uiState,
                    title = stringResource(id = R.string.create_car_ride_destination_address_title),
                    textFieldValue = uiState.destinationAddress,
                    validationNextStep = uiState.waitingAddress.isNotEmpty(),
                    onValueChange = { event(OnDestinationAddress(it)) },
                    onNextAction = {
                        event(OnClearAddressList)
                        event(OnNextStep(CAR_DESTINATION_HOUR))
                    },
                    onBackAction = { event(OnRemoveNewStep(CAR_WAITING_HOUR)) },
                    onItemClicked = { event(OnDestinationAddress(it)) }
                )

                CAR_DESTINATION_HOUR -> StageOfWaitingHourScreen(
                    uiState = uiState,
                    title = stringResource(id = R.string.create_car_ride_destination_hour_title),
                    onValueChange = { event(OnDestinationHour(it)) },
                    onNextAction = { event(OnNextStep(FINISH)) },
                    onBackAction = { event(OnRemoveNewStep(CAR_DESTINATION_ADDRESS)) }
                )
                WAITING_CREATE_RIDE -> {}
                FINISH -> {}
            }
        }
    }
}
