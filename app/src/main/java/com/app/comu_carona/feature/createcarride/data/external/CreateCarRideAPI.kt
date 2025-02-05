package com.app.comu_carona.feature.createcarride.data.external

import org.koin.core.annotation.Factory
import retrofit2.Retrofit
import retrofit2.http.POST
import retrofit2.http.Query

interface CreateCarRideAPI {
    @POST("/api/location/v1/auto-complete-address")
    suspend fun searchAddress(
        @Query("address") address: String
    ): List<String>
}

@Factory(binds = [CreateCarRideAPI::class])
class CreateCarRideAPIImpl(
    retrofit: Retrofit
) : CreateCarRideAPI {
    private val createCarRideAPI = retrofit.create(CreateCarRideAPI::class.java)

    override suspend fun searchAddress(address: String): List<String> =
        createCarRideAPI.searchAddress(address)
}