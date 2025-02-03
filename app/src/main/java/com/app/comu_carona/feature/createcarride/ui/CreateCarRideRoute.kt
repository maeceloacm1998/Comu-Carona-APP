package com.app.comu_carona.feature.createcarride.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.app.comu_carona.feature.createcarride.data.models.CreateCarRideSteps
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
                CreateCarRideSteps.CAR_MODEL -> StageOfCarModelScreen(
                    uiState = uiState,
                    event = event
                )

                CreateCarRideSteps.CAR_QUANTITY_SEATS -> StageOfQuantitySeatsScreen(
                    uiState = uiState,
                    event = event
                )

                CreateCarRideSteps.CAR_WAITING_ADDRESS -> StageOfWaitingAddressScreen(
                    uiState = uiState,
                    event = event
                )

                CreateCarRideSteps.CAR_WAITING_HOUR -> StageOfWaitingHourScreen(
                    uiState = uiState,
                    event = event
                )

                CreateCarRideSteps.CAR_DESTINATION_ADDRESS -> StageOfDestinationAddressScreen(
                    uiState = uiState,
                    event = event
                )

                CreateCarRideSteps.CAR_DESTINATION_HOUR -> StageOfDestinationHourScreen(
                    uiState = uiState,
                    event = event
                )
            }
        }
    }
}
