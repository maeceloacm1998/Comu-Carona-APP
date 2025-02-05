package com.app.comu_carona.feature.createcarride.domain

import com.app.comu_carona.feature.createcarride.data.CreateCarRideRepository
import com.app.comu_carona.feature.createcarride.data.models.CreateCarRideRequest
import org.koin.core.annotation.Factory

@Factory
class CreateCarRideUseCase(
    private val createCarRideRepository: CreateCarRideRepository
) {
    suspend operator fun invoke(
        carModel: String,
        carPlate: String,
        carColor: String,
        quantitySeats: Int,
        waitingAddress: String,
        destinationAddress: String,
        waitingHour: String,
        destinationHour: String,
    ): Result<Unit> {
        val data = CreateCarRideRequest(
            carModel = carModel,
            carPlate = carPlate,
            carColor = carColor,
            quantitySeats = quantitySeats,
            waitingAddress = waitingAddress,
            destinationAddress = destinationAddress,
            waitingHour = waitingHour,
            destinationHour = destinationHour,
            status = "",
            isTwoPassengersBehind = false,
            twoPassengersBehind = false
        )

        return createCarRideRepository.createCarRide(data)
    }
}