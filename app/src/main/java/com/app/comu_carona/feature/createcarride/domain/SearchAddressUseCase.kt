package com.app.comu_carona.feature.createcarride.domain

import com.app.comu_carona.feature.createcarride.data.CreateCarRideRepository
import org.koin.core.annotation.Factory

@Factory
class SearchAddressUseCase(
    private val createCarRideRepository: CreateCarRideRepository
) {
    suspend operator fun invoke(address: String): Result<List<String>> {
        return createCarRideRepository.searchAddress(address)
    }
}