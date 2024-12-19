package com.app.comu_carona.feature.home.data

import org.koin.core.annotation.Factory

@Factory(binds = [HomeRepository::class])
class HomeRepositoryImpl: HomeRepository {

}