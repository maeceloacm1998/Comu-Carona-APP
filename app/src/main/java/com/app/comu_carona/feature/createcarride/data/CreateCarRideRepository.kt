package com.app.comu_carona.feature.createcarride.data

import com.app.comu_carona.feature.createcarride.data.models.CreateCarRideRequest

interface CreateCarRideRepository {
    suspend fun searchAddress(address: String): Result<List<String>>
    suspend fun createCarRide(data: CreateCarRideRequest): Result<Unit>
}