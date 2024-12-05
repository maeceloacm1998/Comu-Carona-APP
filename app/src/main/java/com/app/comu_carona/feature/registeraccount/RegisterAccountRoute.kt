package com.app.comu_carona.feature.registeraccount

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
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
    val registerAccountViewModel: RegisterAccountViewModel = koinViewModel(
        parameters = {
            parametersOf(navController)
        }
    )
    val uiState by registerAccountViewModel.uiState.collectAsStateWithLifecycle()

    RegisterAccountRoute(
        uiState = uiState,
        event = registerAccountViewModel::onEvent
    )
}

@Composable
fun RegisterAccountRoute(
    uiState: RegisterAccountViewModelUiState,
    event: (RegisterAccountViewModelEventState) -> Unit
) {
    check(uiState is RegisterAccountViewModelUiState.Register)

    Column {
        AnimatedContent(
            modifier = Modifier.weight(2f),
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