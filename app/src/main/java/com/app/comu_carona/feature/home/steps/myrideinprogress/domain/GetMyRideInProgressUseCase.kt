package com.app.comu_carona.feature.home.steps.myrideinprogress.domain

import com.app.comu_carona.feature.home.steps.myrideinprogress.data.MyRideInProgressRepository
import com.app.comu_carona.feature.home.steps.rideinprogress.data.models.RideInProgressModel
import org.koin.core.annotation.Factory

@Factory
class GetMyRideInProgressUseCase(
    private val rideInProgressRepository: MyRideInProgressRepository
) {
    suspend operator fun invoke(status: String = ""): Result<List<RideInProgressModel>> =
        rideInProgressRepository.getMyRideInProgress(status)

}