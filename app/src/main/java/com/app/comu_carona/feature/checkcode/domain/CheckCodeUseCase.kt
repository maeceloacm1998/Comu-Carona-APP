package com.app.comu_carona.feature.checkcode.domain

import android.content.Context
import android.util.Log
import com.app.comu_carona.feature.checkcode.data.CheckCodeRepository
import com.app.comu_carona.feature.checkcode.data.model.CheckCodeRequest
import com.app.comu_carona.feature.checkcode.data.model.CheckCodeResponse
import com.app.comu_carona.service.retrofit.AuthPreferences
import com.app.comu_carona.utils.DeviceUtils
import org.koin.core.annotation.Factory
import retrofit2.HttpException

@Factory
class CheckCodeUseCase(
    private val repository: CheckCodeRepository,
    private val authPreferences: AuthPreferences
) {
    suspend operator fun invoke(code: String, context: Context): Result<CheckCodeResponse> {
        val userIdentifier = DeviceUtils.getAdvertisingId(context)

        return try {
            if(userIdentifier != null) {
                val request = CheckCodeRequest(code = code, username = userIdentifier)
                return repository.checkCode(request).fold(
                    onSuccess = { checkCodeResponse ->
                        authPreferences.saveTokens(
                            accessToken = checkCodeResponse.accessToken,
                            refreshToken = checkCodeResponse.refreshToken
                        )
                        Result.success(checkCodeResponse)
                    },
                    onFailure = { throwable ->
                        when (throwable) {
                            is HttpException -> Result.failure(Exception(throwable.code().toString()))
                            else -> Result.failure(throwable)
                        }
                    }
                )
            } else {
                Log.e("CheckCodeUseCase", "User identifier not found")
                Result.failure(Exception("User identifier not found"))
            }
        } catch (e: HttpException) {
            Result.failure(e)
        }
    }
}