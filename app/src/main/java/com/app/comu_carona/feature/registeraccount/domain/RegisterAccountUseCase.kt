package com.app.comu_carona.feature.registeraccount.domain

import android.content.Context
import android.net.Uri
import com.app.comu_carona.feature.registeraccount.data.RegisterAccountRepository
import com.app.comu_carona.feature.registeraccount.data.models.RegisterAccountRequest
import com.app.comu_carona.service.retrofit.AuthPreferences
import com.app.comu_carona.utils.DeviceUtils
import org.koin.core.annotation.Factory

@Factory
class RegisterAccountUseCase(
    private val registerAccountRepository: RegisterAccountRepository,
    private val uploadPhotoUseCase: UploadPhotoUseCase,
    private val authPreferences: AuthPreferences
) {

    suspend operator fun invoke(
        context: Context,
        fullName: String,
        birthDate: String,
        phoneNumber: String,
        photoUri: Uri
    ): Result<Unit> {
        return try {
            val username = DeviceUtils.getAdvertisingId(context)
            val request = RegisterAccountRequest(
                fullName = fullName,
                birthDate = birthDate,
                phoneNumber = phoneNumber
            )

            val registerUserResponse = registerAccountRepository.registerAccount(
                request = request,
                username = username!!
            )

            registerUserResponse.fold(
                onSuccess = { userResponse ->
                    authPreferences.saveTokens(
                        accessToken = userResponse.accessToken,
                        refreshToken = userResponse.refreshToken
                    )

                    val photoUploadResponse = uploadPhotoUseCase(photoUri)
                    photoUploadResponse.fold(
                        onSuccess = { data ->
                            authPreferences.userName = userResponse.username
                            authPreferences.photoUrl = data.uri
                            Result.success(Unit)
                        },
                        onFailure = { throwable ->
                            Result.failure(throwable)
                        }
                    )
                },
                onFailure = { throwable ->
                    Result.failure(throwable)
                }
            )
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}