package com.app.comu_carona.feature.bottomnavigation

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
        BottomNavItem("Home", Routes.Home.route, painterResource(id = R.drawable.ic_home)),
        BottomNavItem("Suas Caronas", Routes.CheckCode.route, painterResource(id = R.drawable.ic_my_car_ride)),
        BottomNavItem("Perfil", Routes.RegisterAccount.route, painterResource(id = R.drawable.ic_profile))
    )

    NavigationBar(
        modifier = Modifier.shadow(elevation = 20.dp),
        containerColor = White,
    ) {
        bottomNavigationItems.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = item.icon,
                        contentDescription = item.name,
                        tint = if(currentRoute == item.route) Primary else DisabledBackground
                    )
                },
                label = {
                    Text(
                        text = item.name,
                        fontWeight = SemiBold,
                        color = if(currentRoute == item.route) Primary else DisabledBackground
                    )
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