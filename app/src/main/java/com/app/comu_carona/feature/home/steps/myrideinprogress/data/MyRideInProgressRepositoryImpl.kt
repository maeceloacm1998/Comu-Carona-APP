package com.app.comu_carona.feature.home.steps.myrideinprogress.data

import com.app.comu_carona.feature.home.steps.myrideinprogress.data.external.MyRideInProgressAPI
import com.app.comu_carona.feature.home.steps.rideinprogress.data.models.RideInProgressModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory

@Factory(binds = [MyRideInProgressRepository::class])
class MyRideInProgressRepositoryImpl(
    private val rideInProgressAPI: MyRideInProgressAPI
): MyRideInProgressRepository {
    override suspend fun getMyRideInProgress(
        status: String?
    ): Result<List<RideInProgressModel>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = rideInProgressAPI.getMyRideInProgress(status = status)
                Result.success(response)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

}