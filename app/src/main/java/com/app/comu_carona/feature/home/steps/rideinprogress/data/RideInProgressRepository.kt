package com.app.comu_carona.feature.home.steps.rideinprogress.data

import com.app.comu_carona.feature.home.steps.rideinprogress.data.models.RideInProgressModel

interface RideInProgressRepository {
    suspend fun getRideInProgress(status: String?): Result<List<RideInProgressModel>>
}