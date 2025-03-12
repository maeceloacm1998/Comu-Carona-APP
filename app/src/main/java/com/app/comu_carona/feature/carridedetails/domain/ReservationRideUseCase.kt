package com.app.comu_carona.feature.carridedetails.domain

import com.app.comu_carona.feature.carridedetails.data.CarRideDetailsRepository
import org.koin.core.annotation.Factory

@Factory
class ReservationRideUseCase(
    private val carRideDetailsRepository: CarRideDetailsRepository
) {
    suspend operator fun invoke(riderId: String) = carRideDetailsRepository.reservationRide(riderId)
}