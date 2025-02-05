package com.app.comu_carona.feature.createcarride.ui

import com.app.comu_carona.feature.createcarride.data.models.CreateCarRideSteps

sealed class CreateCarRideViewModelEventState {
    /**
     * Represents the event when the next step button is clicked.
     *
     * @param step The new step.
     */
    data class OnNextStep(val step: CreateCarRideSteps) : CreateCarRideViewModelEventState()

    /**
     * Represents the event when the previous step button is clicked.
     *
     * @param step The new step.
     */
    data class OnRemoveNewStep(val step: CreateCarRideSteps) :
        CreateCarRideViewModelEventState()

    /**
     * Represents the event when the address list is cleared.
     */
    data object OnClearAddressList : CreateCarRideViewModelEventState()

    /**
     * Represents the event when the car model is updated.
     */
    data class OnCarModel(val carModel: String) : CreateCarRideViewModelEventState()

    /**
     * Represents the event when the car color is updated.
     */
    data class OnCarColor(val carColor: String) : CreateCarRideViewModelEventState()

    /**
     * Represents the event when the car plate is updated.
     */
    data class OnCarPlate(val carPlate: String) : CreateCarRideViewModelEventState()

    /**
     * Represents the event when the quantity seats is updated.
     */
    data class OnQuantitySeats(val quantitySeats: Int) : CreateCarRideViewModelEventState()

    /**
     * Represents the event when the waiting address is updated.
     */
    data class OnWaitingAddress(val waitingAddress: String) : CreateCarRideViewModelEventState()

    /**
     * Represents the event when the destination address is updated.
     */
    data class OnDestinationAddress(val destinationAddress: String) :
        CreateCarRideViewModelEventState()

    /**
     * Represents the event when the waiting hour is updated.
     */
    data class OnWaitingHour(val waitingHour: String) : CreateCarRideViewModelEventState()

    /**
     * Represents the event when the destination hour is updated.
     */
    data class OnDestinationHour(val destinationHour: String) : CreateCarRideViewModelEventState()

    /**
     * Represents the event when the create car ride button is clicked.
     */
    data object OnCreateCarRide : CreateCarRideViewModelEventState()

    /**
     * Represents the event when the search address is clicked.
     */
    data object OnGoToHome : CreateCarRideViewModelEventState()
}