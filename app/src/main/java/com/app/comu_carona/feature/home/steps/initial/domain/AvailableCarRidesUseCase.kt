package com.app.comu_carona.feature.home.steps.initial.domain

import com.app.comu_carona.feature.home.steps.initial.data.InitialRepository
import org.koin.core.annotation.Single

@Single
class AvailableCarRidesUseCase(
    private val initialRepository: InitialRepository
) {
    suspend operator fun invoke() = initialRepository.getAvailableCarRides()
}