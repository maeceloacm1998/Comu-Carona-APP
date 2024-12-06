package com.app.comu_carona

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.app.comu_carona.routes.Routes
import com.app.comu_carona.service.retrofit.AuthPreferences
import com.app.comu_carona.theme.ComuCaronaTheme
import org.koin.compose.koinInject

@Composable
fun ComuCaronaApp(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val authPreferences: AuthPreferences = koinInject()

    val startDestination = when {
        authPreferences.accessToken != "" -> Routes.Home.route
        else -> Routes.CheckCode.route
    }

    ComuCaronaTheme {
        ComuCaronaNavGraph(
            modifier = modifier,
            navController = navController,
            startDestination = startDestination
        )
    }
}