package com.app.comu_carona.feature.rideinprogressDetails.data

import com.app.comu_carona.feature.rideinprogressDetails.data.external.RideInProgressDetailsAPIImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory

@Factory(binds = [RideInProgressDetailsRepository::class])
class RideInProgressDetailsRepositoryImpl(
    private val rideInProgressDetailsAPI: RideInProgressDetailsAPIImpl
): RideInProgressDetailsRepository {
    override suspend fun deleteReservation(riderId: String): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val carRideDetails = rideInProgressDetailsAPI.deleteReservation(riderId)
                Result.success(carRideDetails)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}