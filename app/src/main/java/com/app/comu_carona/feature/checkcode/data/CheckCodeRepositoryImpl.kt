package com.app.comu_carona.feature.checkcode.data

import com.app.comu_carona.feature.checkcode.data.external.CheckCodeAPIImpl
import com.app.comu_carona.feature.checkcode.data.model.CheckCodeRequest
import com.app.comu_carona.feature.checkcode.data.model.CheckCodeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory

@Factory(binds = [CheckCodeRepository::class])
class CheckCodeRepositoryImpl(
    private val checkCodeAPI: CheckCodeAPIImpl
): CheckCodeRepository {
    override suspend fun checkCode(checkCodeRequest: CheckCodeRequest): Result<CheckCodeResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = checkCodeAPI.checkCode(checkCodeRequest)
                Result.success(response)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}