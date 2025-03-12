package com.app.comu_carona.feature.carridedetails.data

import com.app.comu_carona.feature.carridedetails.data.models.CarRideDetails

interface CarRideDetailsRepository {
    suspend fun getCarRideDetails(id: String): Result<CarRideDetails>
    suspend fun reservationRide(riderId: String): Result<Unit>
}