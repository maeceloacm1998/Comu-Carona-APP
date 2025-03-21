package com.app.comu_carona.feature.home.bottomnavigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.unit.dp
import com.app.comu_carona.R
import com.app.comu_carona.routes.Routes
import com.app.comu_carona.theme.DisabledBackground
import com.app.comu_carona.theme.Primary

@Composable
fun BottomNavBar(
    currentRoute: String,
    onItemClick: (route: String) -> Unit
) {
    val bottomNavigationItems = listOf(
        BottomNavItem("Home", Routes.Initial.route, painterResource(id = R.drawable.ic_home)),
        BottomNavItem(
            "Suas reservas",
            Routes.RideInProgress.route,
            painterResource(id = R.drawable.ic_my_car_ride)
        ),
        BottomNavItem(
            "Suas caronas",
            Routes.MyRideInProgress.route,
            painterResource(id = R.drawable.ic_car_ride)
        ),
        BottomNavItem(
            "Perfil",
            Routes.Profile.route,
            painterResource(id = R.drawable.ic_profile)
        )
    )

    NavigationBar(
        modifier = Modifier.shadow(elevation = 20.dp).height(80.dp),
        containerColor = White,
    ) {
        bottomNavigationItems.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        modifier = Modifier.size(23.dp),
                        painter = item.icon,
                        contentDescription = item.name,
                        tint = if (currentRoute == item.route) Primary else DisabledBackground
                    )
                },
                label = {
                    Column(
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            modifier = Modifier.height(30.dp),
                            text = item.name,
                            fontWeight = SemiBold,
                            textAlign = Center,
                            color = if (currentRoute == item.route) Primary else DisabledBackground
                        )
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = White,
                ),
                selected = currentRoute == item.route,
                onClick = { onItemClick(item.route) }
            )
        }
    }
}