package com.app.comu_carona.feature.rideinprogress.data

import com.app.comu_carona.feature.rideinprogress.data.external.RideInProgressAPI
import com.app.comu_carona.feature.rideinprogress.data.models.RideInProgressModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory

@Factory(binds = [RideInProgressRepository::class])
class RideInProgressRepositoryImpl(
    private val rideInProgressAPI: RideInProgressAPI
): RideInProgressRepository {
    override suspend fun getRideInProgress(
        status: String?
    ): Result<List<RideInProgressModel>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = rideInProgressAPI.getRideInProgress(status = status)
                Result.success(response)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

}