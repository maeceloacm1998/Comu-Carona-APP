package com.app.comu_carona.feature.registeraccount.data

import com.app.comu_carona.feature.registeraccount.data.external.RegisterAccountAPI
import com.app.comu_carona.feature.registeraccount.data.models.RegisterAccountRequest
import com.app.comu_carona.feature.registeraccount.data.models.RegisterAccountResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import org.koin.core.annotation.Factory

@Factory(binds = [RegisterAccountRepository::class])
class RegisterAccountRepositoryImpl(
    private val registerAccountAPI: RegisterAccountAPI
) : RegisterAccountRepository {

    override suspend fun registerAccount(
        request: RegisterAccountRequest,
        username: String
    ): Result<RegisterAccountResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = registerAccountAPI.registerAccount(
                    request = request,
                    username = username
                )
                Result.success(response)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    override suspend fun updatePhoto(photoUri: MultipartBody.Part): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val response = registerAccountAPI.uploadImage(photoUri)
                Result.success(response)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}