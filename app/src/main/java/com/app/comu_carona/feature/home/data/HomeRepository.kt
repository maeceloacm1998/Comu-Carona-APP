package com.app.comu_carona.feature.home.data

import com.app.comu_carona.feature.home.data.models.AvailableCarRide
import com.app.comu_carona.service.retrofit.UserInformation

interface HomeRepository {
    suspend fun getAvailableCarRides(): Result<List<AvailableCarRide>>
    suspend fun getUserInformation(): Result<UserInformation>
}