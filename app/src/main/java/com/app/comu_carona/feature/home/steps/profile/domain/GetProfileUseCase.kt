package com.app.comu_carona.feature.home.steps.profile.domain

import com.app.comu_carona.feature.home.steps.profile.data.ProfileRepository
import org.koin.core.annotation.Factory

@Factory
class GetProfileUseCase(
    private val profileRepository: ProfileRepository
) {
    suspend operator fun invoke() = profileRepository.getProfile()
}