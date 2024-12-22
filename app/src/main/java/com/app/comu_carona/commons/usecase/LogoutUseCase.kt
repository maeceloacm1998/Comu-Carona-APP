package com.app.comu_carona.commons.usecase

import androidx.navigation.NavController
import com.app.comu_carona.routes.Routes
import com.app.comu_carona.service.retrofit.AuthPreferences
import org.koin.core.annotation.Single

@Single
class LogoutUseCase(
    private val authPreferences: AuthPreferences,
) {
    operator fun invoke(
        navController: NavController,
        actualRoute: String
    ) {
        authPreferences.clearTokens()
        navController.navigate(Routes.CheckCode.route) {
            popUpTo(actualRoute) {
                inclusive = true
            }
        }
    }
}