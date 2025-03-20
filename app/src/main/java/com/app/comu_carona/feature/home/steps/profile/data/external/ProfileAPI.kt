package com.app.comu_carona.feature.home.steps.profile.data.external

import com.app.comu_carona.feature.registeraccount.data.models.RegisterAccountRequest
import org.koin.core.annotation.Single
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface ProfileAPI {
    @GET("/api/profile/v1/user-profile")
    suspend fun getProfile(): RegisterAccountRequest

    @PUT("/api/profile/v1/user-profile")
    suspend fun updateProfile(
        @Body user: RegisterAccountRequest
    ): RegisterAccountRequest
}

@Single(binds = [ProfileAPI::class])
class ProfileAPIImpl(
    retrofit: Retrofit
) : ProfileAPI {
    private val profileAPI = retrofit.create(ProfileAPI::class.java)

    override suspend fun getProfile(): RegisterAccountRequest = profileAPI.getProfile()
    override suspend fun updateProfile(user: RegisterAccountRequest): RegisterAccountRequest =
        profileAPI.updateProfile(user)
}