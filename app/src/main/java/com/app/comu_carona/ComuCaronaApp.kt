package com.app.comu_carona

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.app.comu_carona.routes.Routes
import com.app.comu_carona.theme.ComuCaronaTheme

@Composable
fun ComuCaronaApp() {
    val navController = rememberNavController()

    ComuCaronaTheme {
        ComuCaronaNavGraph(
            navController = navController,
            startDestination = Routes.CheckCode.route
        )
    }
}