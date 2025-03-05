package com.app.comu_carona.feature.rideinprogress.data

import com.app.comu_carona.feature.rideinprogress.data.models.RideInProgressModel

interface RideInProgressRepository {
    suspend fun getRideInProgress(status: String?): Result<List<RideInProgressModel>>
}