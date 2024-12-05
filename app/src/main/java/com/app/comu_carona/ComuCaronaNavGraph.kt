package com.app.comu_carona

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.comu_carona.feature.checkcode.ui.CheckCodeRoute
import com.app.comu_carona.feature.registeraccount.RegisterAccountRoute
import com.app.comu_carona.routes.Routes

@Composable
fun ComuCaronaNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Routes.RegisterAccount.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        composable(route = Routes.RegisterAccount.route) {
            RegisterAccountRoute(
                navController = navController
            )
        }

        composable(route = Routes.CheckCode.route) {
            CheckCodeRoute(
                navController = navController
            )
        }
    }
}