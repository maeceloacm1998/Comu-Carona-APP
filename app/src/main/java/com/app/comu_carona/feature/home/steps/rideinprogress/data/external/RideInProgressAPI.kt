package com.app.comu_carona.feature.home.steps.rideinprogress.data.external

import com.app.comu_carona.feature.home.steps.rideinprogress.data.models.RideInProgressModel
import org.koin.core.annotation.Factory
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface RideInProgressAPI {

    @GET("/api/car-ride-reservations/v1/my-reservations")
    suspend fun getRideInProgress(
        @Query("status") status: String? = null
    ): List<RideInProgressModel>

}

@Factory(binds = [RideInProgressAPI::class])
class RideInProgressAPImpl(
    retrofit: Retrofit
) : RideInProgressAPI {
    private val rideInProgressAPI = retrofit.create(RideInProgressAPI::class.java)

    override suspend fun getRideInProgress(status: String?): List<RideInProgressModel> =
        rideInProgressAPI.getRideInProgress(status)
}