package com.app.comu_carona.feature.registeraccount.ui

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.app.comu_carona.commons.usecase.LogoutUseCase
import com.app.comu_carona.feature.registeraccount.data.models.RegisterAccountSteps
import com.app.comu_carona.feature.registeraccount.data.models.RegisterAccountSteps.PHOTO
import com.app.comu_carona.feature.registeraccount.domain.RegisterAccountUseCase
import com.app.comu_carona.routes.Routes
import com.app.comu_carona.service.retrofit.NetworkingHttpState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import retrofit2.HttpException

@KoinViewModel
class RegisterAccountViewModel(
    private val navigation: NavController,
    private val registerAccountUseCase: RegisterAccountUseCase,
    private val logoutUseCase: LogoutUseCase
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
            is RegisterAccountViewModelEventState.OnOpenPhoto -> onOpenGallery(event.uri)
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
        val state = viewModelState.value
        onUpdateLoading(true)

        viewModelScope.launch {
            registerAccountUseCase(
                context = navigation.context,
                fullName = state.fullName,
                birthDate = state.birthDate,
                phoneNumber = state.phoneNumber,
                photoUri = state.photoUri
            ).onSuccess {
                onGoToHome()
                onUpdateLoading(false)
                onUpdateSuccess(true)
            }.onFailure { throwable ->
                val errorCode = (throwable as HttpException).code()

                when (errorCode) {
                    NetworkingHttpState.UNAUTHORIZED.code -> {
                        logoutUseCase(
                            navController = navigation,
                            actualRoute = Routes.CreateCarRide.route
                        )
                    }

                    else -> {
                        onUpdateLoading(false)
                        // Colocar Snackbar de erro
                    }
                }
            }
        }
    }

    private fun onOpenGallery(uri: Uri) {
        viewModelState.update { it.copy(photoUri = uri) }
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

    private fun onUpdateLoading(isLoading: Boolean) {
        viewModelState.update { it.copy(isLoading = isLoading) }
    }

    private fun onUpdateSuccess(isSuccess: Boolean) {
        viewModelState.update { it.copy(isSuccess = isSuccess) }
    }

    private fun onGoToHome() {
        navigation.navigate(Routes.Home.route) {
            popUpTo(Routes.RegisterAccount.route) {
                inclusive = true
            }
        }
    }
}