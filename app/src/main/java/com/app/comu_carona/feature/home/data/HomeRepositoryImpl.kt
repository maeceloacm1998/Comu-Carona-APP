package com.app.comu_carona.feature.home.data

import com.app.comu_carona.feature.home.data.external.HomeAPI
import com.app.comu_carona.feature.home.data.models.AvailableCarRide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory

@Factory(binds = [HomeRepository::class])
class HomeRepositoryImpl(
    private val homeAPI: HomeAPI
): HomeRepository {
    override suspend fun getAvailableCarRides(): Result<List<AvailableCarRide>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = homeAPI.getAvailableCarRides()
                Result.success(response)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}