package com.app.comu_carona.feature.checkcode.data

import com.app.comu_carona.feature.checkcode.data.external.CheckCodeAPIImpl
import com.app.comu_carona.feature.checkcode.data.model.CheckCodeRequest
import com.app.comu_carona.feature.checkcode.data.model.CheckCodeResponse
import org.koin.core.annotation.Factory

@Factory(binds = [CheckCodeRepository::class])
class CheckCodeRepositoryImpl(
    private val checkCodeAPI: CheckCodeAPIImpl
): CheckCodeRepository {
    override suspend fun checkCode(checkCodeRequest: CheckCodeRequest): Result<CheckCodeResponse> {
        return checkCodeAPI.checkCode(checkCodeRequest)
    }
}