package com.app.comu_carona

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.comu_carona.feature.checkcode.ui.CheckCodeRoute
import com.app.comu_carona.feature.home.ui.HomeRoute
import com.app.comu_carona.feature.registeraccount.ui.RegisterAccountRoute
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
        composable(route = Routes.Home.route) {
            HomeRoute(
                navController = navController
            )
        }

        composable(route = Routes.CheckCode.route) {
            CheckCodeRoute(
                navController = navController
            )
        }

        composable(route = Routes.RegisterAccount.route) {
            RegisterAccountRoute(
                navController = navController
            )
        }
    }
}