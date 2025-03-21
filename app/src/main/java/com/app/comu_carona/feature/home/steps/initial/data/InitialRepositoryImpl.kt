package com.app.comu_carona.feature.home.steps.initial.data

import com.app.comu_carona.feature.home.steps.initial.data.external.InitialAPI
import com.app.comu_carona.feature.home.steps.initial.data.models.AvailableCarRide
import com.app.comu_carona.service.retrofit.AuthPreferences
import com.app.comu_carona.service.retrofit.UserInformation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory

@Factory(binds = [InitialRepository::class])
class InitialRepositoryImpl(
    private val homeAPI: InitialAPI,
    private val authPreferences: AuthPreferences
): InitialRepository {
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

    override suspend fun getUserInformation(): Result<UserInformation> {
        return withContext(Dispatchers.IO) {
            try {
                val userInformation = UserInformation(
                    name = authPreferences.userName ?: "",
                    photoUrl = authPreferences.photoUrl ?: ""
                )
                Result.success(userInformation)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}