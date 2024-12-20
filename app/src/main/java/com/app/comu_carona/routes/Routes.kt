package com.app.comu_carona.routes

sealed class Routes(val route: String) {
    data object CheckCode : Routes("check_code")
    data object Home : Routes("home")
    data object RegisterAccount : Routes("register_account")
}