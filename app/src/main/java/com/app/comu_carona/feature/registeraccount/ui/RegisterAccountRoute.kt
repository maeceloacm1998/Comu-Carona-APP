package com.app.comu_carona.feature.registeraccount.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.app.comu_carona.components.snackbar.CCSnackbar
import com.app.comu_carona.components.snackbar.SnackbarCustomType.ERROR
import com.app.comu_carona.feature.registeraccount.data.models.RegisterAccountSteps.BIRTH_DATE
import com.app.comu_carona.feature.registeraccount.data.models.RegisterAccountSteps.FULL_NAME
import com.app.comu_carona.feature.registeraccount.data.models.RegisterAccountSteps.PHONE_NUMBER
import com.app.comu_carona.feature.registeraccount.data.models.RegisterAccountSteps.PHOTO
import com.app.comu_carona.utils.AnimatedUtils.animatedTransitionPage
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun RegisterAccountRoute(
    navController: NavController
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val registerAccountViewModel: RegisterAccountViewModel = koinViewModel(
        parameters = {
            parametersOf(navController, snackbarHostState)
        }
    )
    val uiState by registerAccountViewModel.uiState.collectAsStateWithLifecycle()

    RegisterAccountRoute(
        uiState = uiState,
        event = registerAccountViewModel::onEvent,
        snackbarHostState = snackbarHostState
    )
}

@Composable
fun RegisterAccountRoute(
    uiState: RegisterAccountViewModelUiState,
    event: (RegisterAccountViewModelEventState) -> Unit,
    snackbarHostState: SnackbarHostState
) {
    check(uiState is RegisterAccountViewModelUiState.Register)

    Scaffold(
        snackbarHost = {
            CCSnackbar(
                snackbarHostState = snackbarHostState,
                snackbarType = ERROR
            )
        }
    ) { paddingValues ->
        AnimatedContent(
            modifier = Modifier.padding(paddingValues),
            targetState = uiState.steps,
            label = "AnimatedContent",
            transitionSpec = animatedTransitionPage()
        ) { targetState ->
            when (targetState) {
                FULL_NAME -> StageOfFullNameScreen(
                    uiState = uiState,
                    event = event
                )

                BIRTH_DATE -> StageOfBirthDateScreen(
                    uiState = uiState,
                    event = event
                )

                PHONE_NUMBER -> StageOfPhoneNumberScreen(
                    uiState = uiState,
                    event = event
                )

                PHOTO -> StageOfPhotoScreen(
                    uiState = uiState,
                    event = event
                )
            }
        }
    }
}