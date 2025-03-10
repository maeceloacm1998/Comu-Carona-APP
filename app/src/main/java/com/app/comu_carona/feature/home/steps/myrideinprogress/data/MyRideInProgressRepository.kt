package com.app.comu_carona.feature.home.steps.myrideinprogress.data

import com.app.comu_carona.feature.home.steps.rideinprogress.data.models.RideInProgressModel

interface MyRideInProgressRepository {
    suspend fun getMyRideInProgress(status: String?): Result<List<RideInProgressModel>>
}