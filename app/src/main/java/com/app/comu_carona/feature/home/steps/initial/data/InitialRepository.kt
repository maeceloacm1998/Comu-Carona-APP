package com.app.comu_carona.feature.home.steps.initial.data

import com.app.comu_carona.feature.home.steps.initial.data.models.AvailableCarRide
import com.app.comu_carona.service.retrofit.UserInformation

interface InitialRepository {
    suspend fun getAvailableCarRides(): Result<List<AvailableCarRide>>
    suspend fun getUserInformation(): Result<UserInformation>
}