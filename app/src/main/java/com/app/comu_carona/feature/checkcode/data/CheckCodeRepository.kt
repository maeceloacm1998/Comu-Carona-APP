package com.app.comu_carona.feature.checkcode.data

import com.app.comu_carona.feature.checkcode.data.model.CheckCodeRequest
import com.app.comu_carona.feature.checkcode.data.model.CheckCodeResponse

interface CheckCodeRepository {
    suspend fun checkCode(checkCodeRequest: CheckCodeRequest): Result<CheckCodeResponse>
}