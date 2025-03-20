package com.app.comu_carona.feature.home.steps.profile.data

import com.app.comu_carona.feature.registeraccount.data.models.RegisterAccountRequest

interface ProfileRepository {
    suspend fun getProfile(): Result<RegisterAccountRequest>
    suspend fun updateProfile(newUserInformation: RegisterAccountRequest): Result<RegisterAccountRequest>
}