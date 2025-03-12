package com.app.comu_carona.feature.carridedetails.domain

import com.app.comu_carona.feature.carridedetails.data.CarRideDetailsRepository
import org.koin.core.annotation.Factory

@Factory
class GetCarRideDetailsUseCase(
    private val carRideDetailsRepository: CarRideDetailsRepository
) {
    suspend operator fun invoke(id: String) = carRideDetailsRepository.getCarRideDetails(id)
}