package com.app.comu_carona.feature.rideinprogressDetails.data.external

import org.koin.core.annotation.Factory
import retrofit2.Retrofit
import retrofit2.http.DELETE
import retrofit2.http.Path

interface RideInProgressDetailsAPI {
    @DELETE("/api/car-ride-reservations/v1/delete-reservation/{riderId}")
    suspend fun deleteReservation(
        @Path("riderId") riderId: String
    )
}

@Factory(binds = [RideInProgressDetailsAPI::class])
class RideInProgressDetailsAPIImpl(
    retrofit: Retrofit
) : RideInProgressDetailsAPI {
    private val createRideInProgressDetailsAPI =
        retrofit.create(RideInProgressDetailsAPI::class.java)

    override suspend fun deleteReservation(riderId: String) =
        createRideInProgressDetailsAPI.deleteReservation(riderId)
}