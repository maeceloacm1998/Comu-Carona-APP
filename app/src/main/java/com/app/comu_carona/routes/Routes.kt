package com.app.comu_carona.routes

sealed class Routes(val route: String) {
    data object CheckCode : Routes("check_code/{userName}/{photoUrl}")
    data object Home : Routes("home")
    data object RegisterAccount : Routes("register_account/{userName}/{photoUrl}")
    data object CreateCarRide : Routes("create_car_ride")
}

fun Routes.withArgs(vararg args: String): String {
    return route.split("/").zip(args).joinToString("/") { (path, arg) ->
        if (path.startsWith("{") && path.endsWith("}")) arg else path
    }
}