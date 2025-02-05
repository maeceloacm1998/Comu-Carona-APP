package com.app.comu_carona.feature.createcarride.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.comu_carona.feature.createcarride.data.models.CreateCarRideSteps
import com.app.comu_carona.feature.createcarride.data.models.CreateCarRideSteps.FINISH
import com.app.comu_carona.feature.createcarride.domain.SearchAddressUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class CreateCarRideViewModel(
    private val searchAddressUseCase: SearchAddressUseCase
) : ViewModel() {
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
            CreateCarRideViewModelEventState.OnClearAddressList -> onClearAddressList()
        }
    }

    private fun onNextStep(step: CreateCarRideSteps) {
        if (step == FINISH) {
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

    private fun onSearchAddress(
        address: String,
        onSuccess: (List<String>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        viewModelScope.launch {
            searchAddressUseCase(address).onSuccess { addresses ->
                onSuccess(addresses)
            }.onFailure { throwable ->
                onError(throwable)
            }
        }
    }

    private fun onCreateCarRide() {

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
        onSearchAddress(
            address = waitingAddress,
            onSuccess = { addresses ->
                viewModelState.update {
                    it.copy(waitingAddressList = addresses)
                }
            }, onError = {
                viewModelState.update {
                    it.copy(waitingAddressList = emptyList())
                }
            }
        )
        viewModelState.update {
            it.copy(waitingAddress = waitingAddress)
        }
    }

    private fun onUpdateDestinationAddress(destinationAddress: String) {
        onSearchAddress(
            address = destinationAddress,
            onSuccess = { addresses ->
                viewModelState.update {
                    it.copy(waitingAddressList = addresses)
                }
            }, onError = {
                viewModelState.update {
                    it.copy(waitingAddressList = emptyList())
                }
            }
        )

        viewModelState.update {
            it.copy(destinationAddress = destinationAddress)
        }
    }

    private fun onClearAddressList() {
        viewModelState.update {
            it.copy(waitingAddressList = emptyList(), destinationAddressList = emptyList())
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
}