package com.app.comu_carona.feature.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.app.comu_carona.feature.home.bottomnavigation.BottomNavBar
import com.app.comu_carona.feature.home.steps.initial.ui.InitialRoute
import com.app.comu_carona.feature.home.steps.rideinprogress.ui.RideInProgressRoute
import com.app.comu_carona.feature.home.HomeViewModelEventState.OnNavigateTo
import com.app.comu_carona.routes.Routes
import com.app.comu_carona.routes.Routes.Home
import com.app.comu_carona.routes.Routes.Initial
import com.app.comu_carona.utils.AnimatedUtils.animatedTransitionPage
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeRoute(
    navController: NavController
) {
    val viewModel: HomeViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeRoute(
        uiState = uiState,
        onEvent = viewModel::onEvent,
        navController = navController
    )
}

@Composable
fun HomeRoute(
    uiState: HomeViewModelUiState,
    onEvent: (HomeViewModelEventState) -> Unit,
    navController: NavController
) {
    check(uiState is HomeViewModelUiState.Data)

    Scaffold(
        bottomBar = {
            BottomNavBar(
                currentRoute = uiState.steps,
                onItemClick = {
                    onEvent(OnNavigateTo(it))
                }
            )
        }
    ) { innerPadding ->
        AnimatedContent(
            modifier = Modifier
                .background(Color.White)
                .padding(innerPadding)
                .fillMaxSize(),
            targetState = uiState.steps,
            label = "AnimatedContent",
            transitionSpec = animatedTransitionPage()
        ) { targetState ->
            when (targetState) {
                Initial.route -> InitialRoute(
                    navController = navController
                )

                Routes.RideInProgress.route -> RideInProgressRoute(
                    navController = navController
                )

                else -> {
                    InitialRoute(
                        navController = navController
                    )
                }
            }
        }
    }
}