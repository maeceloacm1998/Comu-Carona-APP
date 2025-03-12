package com.app.comu_carona.feature.createcarride.data

import com.app.comu_carona.feature.createcarride.data.models.CreateCarRideRequest
import com.app.comu_carona.feature.createcarride.data.models.LastCarRide

interface CreateCarRideRepository {
    suspend fun searchAddress(address: String): Result<List<String>>
    suspend fun createCarRide(data: CreateCarRideRequest): Result<Unit>
    suspend fun getLastCarRide(): Result<LastCarRide>
}