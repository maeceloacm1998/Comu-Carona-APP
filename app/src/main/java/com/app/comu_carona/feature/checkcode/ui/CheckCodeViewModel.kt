package com.app.comu_carona.feature.checkcode.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.app.comu_carona.feature.checkcode.domain.CheckCodeUseCase
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
class CheckCodeViewModel(
    private val checkCodeUseCase: CheckCodeUseCase,
    private val context: Context,
    private val navController: NavController
) : ViewModel() {
    private val viewModelState = MutableStateFlow(CheckCodeViewModelState(isLoading = false))

    val uiState = viewModelState
        .map(CheckCodeViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    fun onEvent(event: CheckCodeViewModelEventState) {
        when (event) {
            is CheckCodeViewModelEventState.OnChangedCode -> onChangedCode(event.code)
            is CheckCodeViewModelEventState.OnClickCheckCode -> onClickCheckCode()
        }
    }

    private fun onChangedCode(code: List<String>) {
        viewModelState.update { it.copy(code = code) }
    }

    private fun onClickCheckCode() {
        val isEnabled = viewModelState.value.code.all { it.isNotEmpty() }
        if (isEnabled) {
            viewModelScope.launch {
                onUpdateLoadingState(true)
                val result = checkCodeUseCase(
                    code = viewModelState.value.code.joinToString(""),
                    context = context
                )

                result.fold(
                    onSuccess = { response ->
                        onUpdateLoadingState(false)
                        onUpdateSuccessState(true)
                        onGoToHome()
                    },
                    onFailure = { throwable ->
                        val errorCode = (throwable as HttpException).code()
                        when (errorCode) {
                            NetworkingHttpState.UNAUTHORIZED.code -> {
                                // enter when the code is incorrect
                                onUpdateLoadingState(false)
                                onUpdateErrorState(true)
                            }

                            NetworkingHttpState.FORBIDDEN.code -> {
                                // enter when the user identifier is incorrect
                                onUpdateLoadingState(false)
                                onUpdateErrorState(false)
                                onUpdateSuccessState(true)
                                onGoToRegisterAccount()
                            }
                        }
                    }
                )
            }
        }
    }

    private fun onGoToHome() {
        navController.navigate(Routes.Home.route) {
            popUpTo(Routes.CheckCode.route) {
                inclusive = true
            }
        }
    }

    private fun onGoToRegisterAccount() {
        navController.navigate(Routes.RegisterAccount.route) {
            popUpTo(Routes.CheckCode.route) {
                inclusive = true
            }
        }
    }

    private fun onUpdateLoadingState(isLoading: Boolean) {
        viewModelState.update { it.copy(isLoading = isLoading) }
    }

    private fun onUpdateSuccessState(isSuccess: Boolean) {
        viewModelState.update { it.copy(isSuccess = isSuccess) }
    }

    private fun onUpdateErrorState(isError: Boolean) {
        viewModelState.update { it.copy(isError = isError) }
    }


}