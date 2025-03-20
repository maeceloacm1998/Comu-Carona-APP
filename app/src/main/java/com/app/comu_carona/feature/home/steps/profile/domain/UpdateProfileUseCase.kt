package com.app.comu_carona.feature.home.steps.profile.domain

import com.app.comu_carona.feature.home.steps.profile.data.ProfileRepository
import com.app.comu_carona.feature.registeraccount.data.models.RegisterAccountRequest
import org.koin.core.annotation.Factory

@Factory
class UpdateProfileUseCase(
    private val profileRepository: ProfileRepository,
) {
    suspend operator fun invoke(
        userName: String,
        birthDate: String,
        phoneNumber: String
    ): Result<RegisterAccountRequest> {
        val newRegisterUpdate = RegisterAccountRequest(
            fullName = userName,
            birthDate = birthDate,
            phoneNumber = phoneNumber
        )

        return profileRepository.updateProfile(newRegisterUpdate)
    }
}