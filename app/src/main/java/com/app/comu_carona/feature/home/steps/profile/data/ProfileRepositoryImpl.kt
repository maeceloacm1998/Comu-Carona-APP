package com.app.comu_carona.feature.home.steps.profile.data

import com.app.comu_carona.feature.home.steps.profile.data.external.ProfileAPI
import com.app.comu_carona.feature.registeraccount.data.models.RegisterAccountRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory

@Factory(binds = [ProfileRepository::class])
class ProfileRepositoryImpl(
    private val profileAPI: ProfileAPI
): ProfileRepository {
    override suspend fun getProfile(): Result<RegisterAccountRequest> {
        return withContext(Dispatchers.IO) {
            try {
                val response = profileAPI.getProfile()
                Result.success(response)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    override suspend fun updateProfile(newUserInformation: RegisterAccountRequest): Result<RegisterAccountRequest> {
        return withContext(Dispatchers.IO) {
            try {
                val response = profileAPI.updateProfile(newUserInformation)
                Result.success(response)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}