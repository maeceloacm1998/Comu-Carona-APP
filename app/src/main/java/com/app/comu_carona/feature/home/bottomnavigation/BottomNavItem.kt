package com.app.comu_carona.feature.home.bottomnavigation

import androidx.compose.ui.graphics.painter.Painter

data class BottomNavItem<T : Any>(val name: String, val route: T, val icon: Painter)