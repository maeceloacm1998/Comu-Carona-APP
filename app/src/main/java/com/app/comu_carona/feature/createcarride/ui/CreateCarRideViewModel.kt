package com.app.comu_carona.feature.createcarride.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.comu_carona.feature.createcarride.data.models.CreateCarRideSteps
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class CreateCarRideViewModel : ViewModel() {
    private val viewModelState = MutableStateFlow(CreateCarRideViewModelState())
    private val stepsOrder: List<CreateCarRideSteps> =
        CreateCarRideSteps.entries.toTypedArray().toList()

    val uiState = viewModelState
        .map(CreateCarRideViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    fun onEvent(event: CreateCarRideViewModelEventState) {
        when (event) {
            is CreateCarRideViewModelEventState.OnNextStep -> onNextStep(event.step)
            is CreateCarRideViewModelEventState.OnRemoveNewStep -> onRemoveNewStep(event.step)
            is CreateCarRideViewModelEventState.OnCarModel -> onUpdateCarModel(event.carModel)
            is CreateCarRideViewModelEventState.OnCarColor -> onUpdateCarColor(event.carColor)
            is CreateCarRideViewModelEventState.OnCarPlate -> onUpdateCarPlate(event.carPlate)
            is CreateCarRideViewModelEventState.OnQuantitySeats -> onUpdateQuantitySeats(event.quantitySeats)
            is CreateCarRideViewModelEventState.OnWaitingAddress -> onUpdateWaitingAddress(event.waitingAddress)
            is CreateCarRideViewModelEventState.OnDestinationAddress -> onUpdateDestinationAddress(
                event.destinationAddress
            )

            is CreateCarRideViewModelEventState.OnWaitingHour -> onUpdateWaitingHour(event.waitingHour)
            is CreateCarRideViewModelEventState.OnDestinationHour -> onUpdateDestinationHour(event.destinationHour)
        }
    }

    private fun onNextStep(step: CreateCarRideSteps) {
        if (step == CreateCarRideSteps.CAR_DESTINATION_HOUR) {
            onCreateCarRide()
        } else {
            val index = stepsOrder.indexOf(step)
            onUpdateStep(stepsOrder[index])
        }
    }

    private fun onRemoveNewStep(step: CreateCarRideSteps) {
        val index = stepsOrder.indexOf(step)

        onUpdateStep(stepsOrder[index])
    }

    private fun onUpdateEnabledCarModelScreen() {
        val carModel = viewModelState.value.carModel
        val carPlate = viewModelState.value.carPlate
        val carColor = viewModelState.value.carColor

        viewModelState.update {
            it.copy(enabledCarModelScreen = carModel.isNotEmpty() && carPlate.isNotEmpty() && carColor.isNotEmpty())
        }
    }

    private fun onUpdateStep(step: CreateCarRideSteps) {
        viewModelState.update { it.copy(steps = step) }
    }

    private fun onUpdateCarModel(carModel: String) {
        onUpdateEnabledCarModelScreen()
        viewModelState.update {
            it.copy(carModel = carModel)
        }
    }

    private fun onUpdateCarColor(carColor: String) {
        onUpdateEnabledCarModelScreen()
        viewModelState.update {
            it.copy(carColor = carColor)
        }
    }

    private fun onUpdateCarPlate(carPlate: String) {
        onUpdateEnabledCarModelScreen()
        viewModelState.update {
            it.copy(carPlate = carPlate)
        }
    }

    private fun onUpdateQuantitySeats(quantitySeats: Int) {
        viewModelState.update {
            it.copy(quantitySeats = quantitySeats)
        }
    }

    private fun onUpdateWaitingAddress(waitingAddress: String) {
        viewModelState.update {
            it.copy(waitingAddress = waitingAddress)
        }
    }

    private fun onUpdateDestinationAddress(destinationAddress: String) {
        viewModelState.update {
            it.copy(destinationAddress = destinationAddress)
        }
    }

    private fun onUpdateWaitingHour(waitingHour: String) {
        viewModelState.update {
            it.copy(waitingHour = waitingHour)
        }
    }

    private fun onUpdateDestinationHour(destinationHour: String) {
        viewModelState.update {
            it.copy(destinationHour = destinationHour)
        }
    }

    private fun onCreateCarRide() {

    }
}