package com.app.comu_carona.feature.home.data.external

import com.app.comu_carona.feature.home.data.models.AvailableCarRide
import org.koin.core.annotation.Single
import retrofit2.Retrofit
import retrofit2.http.GET

interface HomeAPI {
    @GET("/api/car-ride/v1/available")
    suspend fun getAvailableCarRides(): List<AvailableCarRide>
}

@Single(binds = [HomeAPI::class])
class HomeAPIImpl(
    retrofit: Retrofit
) : HomeAPI {
    private val checkCodeAPI = retrofit.create(HomeAPI::class.java)

    override suspend fun getAvailableCarRides(): List<AvailableCarRide> =
        checkCodeAPI.getAvailableCarRides()
}