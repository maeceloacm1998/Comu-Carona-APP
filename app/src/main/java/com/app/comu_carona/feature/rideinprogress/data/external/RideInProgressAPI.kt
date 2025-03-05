package com.app.comu_carona.feature.rideinprogress.data.external

import com.app.comu_carona.feature.registeraccount.data.models.RegisterAccountResponse
import com.app.comu_carona.feature.rideinprogress.data.models.RideInProgressModel
import org.koin.core.annotation.Factory
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path


interface RideInProgressAPI {

    @GET("/api/car-ride-reservations/v1/my-reservations")
    suspend fun getRideInProgress(
        @Path("status") status: String? = ""
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