package com.app.comu_carona.feature.registeraccount.data.external

import com.app.comu_carona.feature.registeraccount.data.models.PhotoRequest
import com.app.comu_carona.feature.registeraccount.data.models.RegisterAccountRequest
import com.app.comu_carona.feature.registeraccount.data.models.RegisterAccountResponse
import okhttp3.MultipartBody
import org.koin.core.annotation.Factory
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path


interface RegisterAccountAPI {

    @POST("auth/signup/{username}")
    suspend fun registerAccount(
        @Body request: RegisterAccountRequest,
        @Path("username") username: String
    ): RegisterAccountResponse

    @Multipart
    @POST("api/files/v1/upload/user-image")
    suspend fun uploadImage(
        @Part file: MultipartBody.Part
    ): PhotoRequest
}

@Factory(binds = [RegisterAccountAPI::class])
class RegisterAccountAPIImpl(
    retrofit: Retrofit
) : RegisterAccountAPI {
    private val registerAccountAPI = retrofit.create(RegisterAccountAPI::class.java)

    override suspend fun registerAccount(
        request: RegisterAccountRequest,
        username: String
    ): RegisterAccountResponse = registerAccountAPI.registerAccount(request, username)

    override suspend fun uploadImage(file: MultipartBody.Part): PhotoRequest =
        registerAccountAPI.uploadImage(file)
}