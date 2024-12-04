package com.app.comu_carona

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.app.comu_carona.routes.Routes
import com.app.comu_carona.theme.ComuCaronaTheme

@Composable
fun ComuCaronaApp(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    ComuCaronaTheme {
        ComuCaronaNavGraph(
            modifier = modifier,
            navController = navController,
            startDestination = Routes.CheckCode.route
        )
    }
}