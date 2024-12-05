package com.app.comu_carona.feature.registeraccount.data

import org.koin.core.annotation.Factory

@Factory(binds = [RegisterAccountRepository::class])
class RegisterAccountRepositoryImpl: RegisterAccountRepository {
}