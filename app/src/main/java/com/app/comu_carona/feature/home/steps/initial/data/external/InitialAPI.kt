package com.app.comu_carona.feature.home.steps.initial.data.external

import com.app.comu_carona.feature.home.steps.initial.data.models.AvailableCarRide
import org.koin.core.annotation.Single
import retrofit2.Retrofit
import retrofit2.http.GET

interface InitialAPI {
    @GET("/api/car-ride/v1/available")
    suspend fun getAvailableCarRides(): List<AvailableCarRide>
}

@Single(binds = [InitialAPI::class])
class HomeAPIImpl(
    retrofit: Retrofit
) : InitialAPI {
    private val checkCodeAPI = retrofit.create(InitialAPI::class.java)

    override suspend fun getAvailableCarRides(): List<AvailableCarRide> =
        checkCodeAPI.getAvailableCarRides()
}