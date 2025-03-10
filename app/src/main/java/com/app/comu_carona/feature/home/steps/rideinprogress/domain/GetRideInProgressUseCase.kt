package com.app.comu_carona.feature.home.steps.rideinprogress.domain

import com.app.comu_carona.feature.home.steps.rideinprogress.data.RideInProgressRepository
import com.app.comu_carona.feature.home.steps.rideinprogress.data.models.RideInProgressModel
import org.koin.core.annotation.Factory

@Factory
class GetRideInProgressUseCase(
    private val rideInProgressRepository: RideInProgressRepository
) {
    suspend operator fun invoke(status: String = ""): Result<List<RideInProgressModel>> =
        rideInProgressRepository.getRideInProgress(status)

}