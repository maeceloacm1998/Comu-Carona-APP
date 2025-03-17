package com.app.comu_carona.feature.myrideinprogressdetails.data.external

import org.koin.core.annotation.Factory
import retrofit2.Retrofit
import retrofit2.http.DELETE
import retrofit2.http.Path

interface MyRideInProgressDetailsAPI {
    @DELETE("/api/car-ride/v1/delete/{riderId}")
    suspend fun deleteCarRide(
        @Path("riderId") riderId: String
    )
}

@Factory(binds = [MyRideInProgressDetailsAPI::class])
class MyRideInProgressDetailsAPIImpl(
    retrofit: Retrofit
) : MyRideInProgressDetailsAPI {
    private val createMyRideInProgressDetailsAPI =
        retrofit.create(MyRideInProgressDetailsAPI::class.java)

    override suspend fun deleteCarRide(riderId: String) =
        createMyRideInProgressDetailsAPI.deleteCarRide(riderId)
}