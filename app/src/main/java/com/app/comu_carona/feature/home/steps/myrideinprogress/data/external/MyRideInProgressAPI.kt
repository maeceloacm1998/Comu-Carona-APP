package com.app.comu_carona.feature.home.steps.myrideinprogress.data.external

import com.app.comu_carona.feature.home.steps.rideinprogress.data.models.RideInProgressModel
import org.koin.core.annotation.Factory
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface MyRideInProgressAPI {

    @GET("/api/car-ride-reservations/v1/my-car-rides")
    suspend fun getMyRideInProgress(
        @Query("status") status: String? = null
    ): List<RideInProgressModel>
}

@Factory(binds = [MyRideInProgressAPI::class])
class MyRideInProgressAPImpl(
    retrofit: Retrofit
) : MyRideInProgressAPI {
    private val myRideInProgressAPI = retrofit.create(MyRideInProgressAPI::class.java)

    override suspend fun getMyRideInProgress(status: String?): List<RideInProgressModel> =
        myRideInProgressAPI.getMyRideInProgress(status)
}