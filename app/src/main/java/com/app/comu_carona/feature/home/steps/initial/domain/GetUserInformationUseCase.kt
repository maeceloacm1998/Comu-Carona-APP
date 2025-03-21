package com.app.comu_carona.feature.home.steps.initial.domain

import com.app.comu_carona.feature.home.steps.initial.data.InitialRepository
import com.app.comu_carona.service.retrofit.UserInformation
import org.koin.core.annotation.Factory

@Factory
class GetUserInformationUseCase(
    private val initialRepository: InitialRepository
) {
    suspend operator fun invoke(): Result<UserInformation> {
        return initialRepository.getUserInformation()
    }
}