package com.app.comu_carona.feature.home.data

import com.app.comu_carona.feature.home.data.models.AvailableCarRide

interface HomeRepository {
    suspend fun getAvailableCarRides(): Result<List<AvailableCarRide>>
}