package com.app.comu_carona.feature.createcarride.data.external

import com.app.comu_carona.feature.createcarride.data.models.CreateCarRideRequest
import com.app.comu_carona.feature.createcarride.data.models.LastCarRide
import org.koin.core.annotation.Factory
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CreateCarRideAPI {
    @POST("/api/location/v1/auto-complete-address")
    suspend fun searchAddress(
        @Query("address") address: String
    ): List<String>

    @POST("/api/car-ride/v1/create")
    suspend fun createCarRide(
        @Body data: CreateCarRideRequest
    )

    @GET("/api/car-ride/v1/find-last")
    suspend fun getLastCarRide(): LastCarRide
}

@Factory(binds = [CreateCarRideAPI::class])
class CreateCarRideAPIImpl(
    retrofit: Retrofit
) : CreateCarRideAPI {
    private val createCarRideAPI = retrofit.create(CreateCarRideAPI::class.java)

    override suspend fun searchAddress(address: String): List<String> =
        createCarRideAPI.searchAddress(address)

    override suspend fun createCarRide(data: CreateCarRideRequest) {
        createCarRideAPI.createCarRide(data)
    }

    override suspend fun getLastCarRide(): LastCarRide =
        createCarRideAPI.getLastCarRide()
}