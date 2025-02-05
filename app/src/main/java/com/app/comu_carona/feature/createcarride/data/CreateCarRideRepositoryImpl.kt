package com.app.comu_carona.feature.createcarride.data

import com.app.comu_carona.feature.createcarride.data.external.CreateCarRideAPI
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
}