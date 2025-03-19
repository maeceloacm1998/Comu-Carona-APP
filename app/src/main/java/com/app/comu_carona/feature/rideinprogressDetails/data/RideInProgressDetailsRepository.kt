package com.app.comu_carona.feature.rideinprogressDetails.data

interface RideInProgressDetailsRepository {
    suspend fun deleteReservation(riderId: String): Result<Unit>
}