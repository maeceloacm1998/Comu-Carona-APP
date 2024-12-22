package com.app.comu_carona.feature.home.domain

import com.app.comu_carona.feature.home.data.HomeRepository
import org.koin.core.annotation.Single

@Single
class AvailableCarRidesUseCase(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke() = homeRepository.getAvailableCarRides()
}