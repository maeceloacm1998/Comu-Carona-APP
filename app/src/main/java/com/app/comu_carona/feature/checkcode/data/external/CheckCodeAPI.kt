package com.app.comu_carona.feature.checkcode.data.external

import com.app.comu_carona.feature.checkcode.data.model.CheckCodeRequest
import com.app.comu_carona.feature.checkcode.data.model.CheckCodeResponse
import org.koin.core.annotation.Single
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.POST

interface CheckCodeAPI {
    @POST("auth/signin")
    suspend fun checkCode(@Body checkCodeRequest: CheckCodeRequest): Result<CheckCodeResponse>
}

@Single(binds = [CheckCodeAPI::class])
class CheckCodeAPIImpl(
    retrofit: Retrofit
) : CheckCodeAPI {
    private val checkCodeAPI = retrofit.create(CheckCodeAPI::class.java)

    override suspend fun checkCode(checkCodeRequest: CheckCodeRequest): Result<CheckCodeResponse> =
        checkCodeAPI.checkCode(checkCodeRequest)
}