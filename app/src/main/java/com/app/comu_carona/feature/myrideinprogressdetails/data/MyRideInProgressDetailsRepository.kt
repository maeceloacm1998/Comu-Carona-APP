package com.app.comu_carona.feature.myrideinprogressdetails.data

interface MyRideInProgressDetailsRepository {
    suspend fun deleteCarRide(riderId: String): Result<Unit>
}