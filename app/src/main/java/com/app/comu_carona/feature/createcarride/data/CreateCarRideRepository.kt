package com.app.comu_carona.feature.createcarride.data

interface CreateCarRideRepository {
    suspend fun searchAddress(address: String): Result<List<String>>
}