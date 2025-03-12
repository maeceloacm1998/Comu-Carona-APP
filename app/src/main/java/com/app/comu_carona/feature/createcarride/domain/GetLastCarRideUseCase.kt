package com.app.comu_carona.feature.createcarride.domain

import com.app.comu_carona.feature.createcarride.data.CreateCarRideRepository
import com.app.comu_carona.feature.createcarride.data.models.LastCarRide
import org.koin.core.annotation.Factory

@Factory
class GetLastCarRideUseCase(
    private val createCarRideRepository: CreateCarRideRepository
) {
    suspend operator fun invoke(): Result<LastCarRide> = createCarRideRepository.getLastCarRide()
}