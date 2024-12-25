package com.app.comu_carona.feature.home.domain

import com.app.comu_carona.feature.home.data.HomeRepository
import com.app.comu_carona.service.retrofit.UserInformation
import org.koin.core.annotation.Single

@Single
class GetUserInformation(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(): Result<UserInformation> {
        return homeRepository.getUserInformation()
    }
}