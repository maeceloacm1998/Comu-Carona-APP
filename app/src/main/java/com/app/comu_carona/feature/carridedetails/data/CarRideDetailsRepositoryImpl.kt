package com.app.comu_carona.feature.carridedetails.data

import com.app.comu_carona.feature.carridedetails.data.external.CarRideDetailsAPI
import com.app.comu_carona.feature.carridedetails.data.models.CarRideDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory

@Factory(binds = [CarRideDetailsRepository::class])
class CarRideDetailsRepositoryImpl(
    private val carRideDetailsAPI: CarRideDetailsAPI
) : CarRideDetailsRepository {
    override suspend fun getCarRideDetails(id: String): Result<CarRideDetails> {
        return withContext(Dispatchers.IO) {
            try {
                val carRideDetails = carRideDetailsAPI.getCarRideDetails(id)
                Result.success(carRideDetails)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    override suspend fun reservationRide(riderId: String): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                carRideDetailsAPI.reservationRide(riderId)
                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}