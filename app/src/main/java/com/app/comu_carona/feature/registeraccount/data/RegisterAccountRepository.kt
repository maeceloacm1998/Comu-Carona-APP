package com.app.comu_carona.feature.registeraccount.data

import com.app.comu_carona.feature.registeraccount.data.models.RegisterAccountRequest
import com.app.comu_carona.feature.registeraccount.data.models.RegisterAccountResponse
import okhttp3.MultipartBody

interface RegisterAccountRepository {
    suspend fun registerAccount(
        request: RegisterAccountRequest,
        username: String
    ): Result<RegisterAccountResponse>

    suspend fun updatePhoto(photoUri: MultipartBody.Part): Result<Unit>
}