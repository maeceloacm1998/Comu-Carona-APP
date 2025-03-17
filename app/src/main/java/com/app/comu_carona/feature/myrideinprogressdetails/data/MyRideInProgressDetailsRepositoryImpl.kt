package com.app.comu_carona.feature.myrideinprogressdetails.data

import com.app.comu_carona.feature.myrideinprogressdetails.data.external.MyRideInProgressDetailsAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory

@Factory(binds = [MyRideInProgressDetailsRepository::class])
class MyRideInProgressDetailsRepositoryImpl(
    private val myCarRideInProgressDetailsAPI: MyRideInProgressDetailsAPI
): MyRideInProgressDetailsRepository {
    override suspend fun deleteCarRide(riderId: String): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val carRideDetails = myCarRideInProgressDetailsAPI.deleteCarRide(riderId)
                Result.success(carRideDetails)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}