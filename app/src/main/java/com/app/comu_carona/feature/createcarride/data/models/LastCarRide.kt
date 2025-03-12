package com.app.comu_carona.feature.createcarride.data.models

import com.app.comu_carona.feature.home.steps.initial.data.models.AvailableCarRide

data class LastCarRide (
    val availableCarRide: AvailableCarRide,
    val carRideInformation: CreateCarRideRequest
)