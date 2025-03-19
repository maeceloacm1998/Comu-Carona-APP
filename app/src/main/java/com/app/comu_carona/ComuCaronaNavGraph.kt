package com.app.comu_carona

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.app.comu_carona.feature.carridedetails.ui.CarRideDetailsRoute
import com.app.comu_carona.feature.checkcode.ui.CheckCodeRoute
import com.app.comu_carona.feature.createcarride.ui.CreateCarRideRoute
import com.app.comu_carona.feature.home.HomeRoute
import com.app.comu_carona.feature.myrideinprogressdetails.ui.MyRideInProgressDetailsRoute
import com.app.comu_carona.feature.registeraccount.ui.RegisterAccountRoute
import com.app.comu_carona.feature.rideinprogressDetails.ui.RideInProgressDetailsRoute
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

        composable(route = Routes.CreateCarRide.route) {
            CreateCarRideRoute(
                navController = navController
            )
        }

        composable(
            route = Routes.CarRideDetails.route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            CarRideDetailsRoute(
                navController = navController,
                backStackEntry = backStackEntry
            )
        }

        composable(
            route = Routes.MyRideInProgressDetails.route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            MyRideInProgressDetailsRoute(
                navController = navController,
                backStackEntry = backStackEntry
            )
        }

        composable(
            route = Routes.RideInProgressDetails.route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            RideInProgressDetailsRoute(
                navController = navController,
                backStackEntry = backStackEntry
            )
        }
    }
}