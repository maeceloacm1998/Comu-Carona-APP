package com.app.comu_carona.feature.rideinprogressDetails.domain

import com.app.comu_carona.feature.rideinprogressDetails.data.RideInProgressDetailsRepository
import org.koin.core.annotation.Factory

@Factory
class DeleteReservationsUseCase(
    private val repository: RideInProgressDetailsRepository
) {
    suspend operator fun invoke(riderId: String) = repository.deleteReservation(riderId)
}