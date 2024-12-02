package com.app.comu_carona

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.comu_carona.feature.checkcode.CheckCodeRoute
import com.app.comu_carona.routes.Routes

@Composable
fun ComuCaronaNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Routes.CheckCode.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        composable(route = Routes.CheckCode.route) {
            CheckCodeRoute()
        }
    }
}