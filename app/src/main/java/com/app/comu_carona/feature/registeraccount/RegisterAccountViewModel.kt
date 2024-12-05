package com.app.comu_carona.feature.registeraccount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.app.comu_carona.feature.registeraccount.data.models.RegisterAccountSteps
import com.app.comu_carona.feature.registeraccount.data.models.RegisterAccountSteps.PHOTO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class RegisterAccountViewModel(
    private val navigation: NavController
): ViewModel() {
    private val viewModelState = MutableStateFlow(RegisterAccountViewModelState())
    private val stepsOrder: List<RegisterAccountSteps> = RegisterAccountSteps.entries.toTypedArray().toList()

    val uiState = viewModelState
        .map(RegisterAccountViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    fun onEvent(event: RegisterAccountViewModelEventState) {
        when(event) {
            is RegisterAccountViewModelEventState.OnNextStep -> onNextStep(event.step)
            is RegisterAccountViewModelEventState.OnRemoveNewStep -> onRemoveNewStep(event.step)
            is RegisterAccountViewModelEventState.OnGrantedPermission -> onGrantedPermission(event.isGranted)
            is RegisterAccountViewModelEventState.OnUpdateFullName -> onUpdateFullName(event.fullName)
            is RegisterAccountViewModelEventState.OnUpdateBirthDate -> onUpdateBirthDate(event.birthDate)
            is RegisterAccountViewModelEventState.OnUpdatePhoneNumber -> onUpdatePhoneNumber(event.phoneNumber)
            is RegisterAccountViewModelEventState.OnUpdatePhoto -> {}
            else -> {}
        }
    }

    private fun onNextStep(step: RegisterAccountSteps) {
        if(step == PHOTO) {
            onRegisterUser()
        } else {
            val index = stepsOrder.indexOf(step)
            onUpdateStep(stepsOrder[index + 1])
        }
    }

    private fun onRemoveNewStep(step: RegisterAccountSteps) {
        val index = stepsOrder.indexOf(step)

        if (index > 0) {
            onUpdateStep(stepsOrder[index - 1])
        }
    }

    private fun onRegisterUser() {

    }

    private fun onUpdateStep(step: RegisterAccountSteps) {
        viewModelState.update { it.copy(steps = step) }
    }

    private fun onGrantedPermission(isGranted: Boolean) {
        viewModelState.update { it.copy(isGrantedPermission = isGranted) }
    }

    private fun onUpdateFullName(fullName: String) {
        viewModelState.update { it.copy(fullName = fullName) }
    }

    private fun onUpdateBirthDate(birthDate: String) {
        viewModelState.update { it.copy(birthDate = birthDate) }
    }

    private fun onUpdatePhoneNumber(phoneNumber: String) {
        viewModelState.update { it.copy(phoneNumber = phoneNumber) }
    }

    private fun onGoToHome() {
//        navigation.navigate(Routes.Chat.route) {
//            popUpTo(Routes.Onboarding.route) {
//                inclusive = true
//            }
//        }
    }
}