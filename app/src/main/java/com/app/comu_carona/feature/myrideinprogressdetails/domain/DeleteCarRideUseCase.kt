package com.app.comu_carona.feature.myrideinprogressdetails.domain

import com.app.comu_carona.feature.myrideinprogressdetails.data.MyRideInProgressDetailsRepository
import org.koin.core.annotation.Factory

@Factory
class DeleteCarRideUseCase(
    private val myRideInProgressDetailsRepository: MyRideInProgressDetailsRepository
) {
    suspend operator fun invoke(riderId: String) = myRideInProgressDetailsRepository.deleteCarRide(riderId)
}