package com.app.comu_carona.feature.createcarride.data

import com.app.comu_carona.feature.createcarride.data.external.CreateCarRideAPI
import com.app.comu_carona.feature.createcarride.data.models.CreateCarRideRequest
import com.app.comu_carona.feature.createcarride.data.models.LastCarRide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Single

@Single(binds = [CreateCarRideRepository::class])
class CreateCarRideRepositoryImpl(
    private val createCarRideAPI: CreateCarRideAPI
): CreateCarRideRepository {
    override suspend fun searchAddress(address: String): Result<List<String>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = createCarRideAPI.searchAddress(address)
                Result.success(response)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    override suspend fun createCarRide(data: CreateCarRideRequest): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val response = createCarRideAPI.createCarRide(data)
                Result.success(response)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    override suspend fun getLastCarRide(): Result<LastCarRide> {
        return withContext(Dispatchers.IO) {
            try {
                val response = createCarRideAPI.getLastCarRide()
                Result.success(response)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}