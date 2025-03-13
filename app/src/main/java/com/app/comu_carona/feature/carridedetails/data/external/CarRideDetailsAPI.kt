package com.app.comu_carona.feature.carridedetails.data.external

import com.app.comu_carona.feature.carridedetails.data.models.CarRideDetails
import org.koin.core.annotation.Factory
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CarRideDetailsAPI {
    @GET("/api/car-ride/v1/details/{id}")
    suspend fun getCarRideDetails(
        @Path("id") id: String
    ): CarRideDetails

    @POST("/api/car-ride/v1/details/reservationRide/{riderId}")
    suspend fun reservationRide(
        @Path("riderId") riderId: String
    )
}

@Factory(binds = [CarRideDetailsAPI::class])
class CarRideDetailsAPIImpl(
    retrofit: Retrofit
) : CarRideDetailsAPI {
    private val createCarRideAPI = retrofit.create(CarRideDetailsAPI::class.java)

    override suspend fun getCarRideDetails(id: String): CarRideDetails =
        createCarRideAPI.getCarRideDetails(id)

    override suspend fun reservationRide(riderId: String) =
        createCarRideAPI.reservationRide(riderId)
}