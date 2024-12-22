package com.app.comu_carona.feature.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.app.comu_carona.commons.usecase.LogoutUseCase
import com.app.comu_carona.feature.home.data.models.AvailableCarRide
import com.app.comu_carona.feature.home.domain.AvailableCarRidesUseCase
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
class HomeViewModel(
    private val availableCarRidesUseCase: AvailableCarRidesUseCase,
    private val navController: NavController,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {
    private val viewModelState = MutableStateFlow(HomeViewModelState())

    init {
        onLoadAvailableCarRide()
    }

    val uiState = viewModelState
        .map(HomeViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    fun onEvent(event: HomeViewModelEventState) {
        when (event) {
            is HomeViewModelEventState.OnLoadAvailableCarRide -> onLoadAvailableCarRide()
            is HomeViewModelEventState.OnNavigateTo -> onNavigateTo(event.route)
        }
    }

    private fun onLoadAvailableCarRide() {
        viewModelScope.launch {
            onUpdateLoadingState(true)

            val result = availableCarRidesUseCase()

            result.fold(
                onSuccess = { availableCarRides ->
                    onUpdateLoadingState(false)
                    onUpdateErrorState(false)
                    onUpdateAvailableCarRides(availableCarRides)
                },
                onFailure = { throwable ->
                    val errorCode = (throwable as HttpException).code()

                    when (errorCode) {
                        NetworkingHttpState.UNAUTHORIZED.code -> {
                            logoutUseCase(navController, Routes.Home.route)
                        }

                        else -> {
                            onUpdateLoadingState(false)
                            onUpdateErrorState(true)
                        }
                    }
                }
            )
        }
    }

    private fun onNavigateTo(route: String) {
        navController.navigate(route)
    }

    private fun onGoToCheckCode() {
        navController.navigate(Routes.CheckCode.route) {
            popUpTo(Routes.Home.route) {
                inclusive = true
            }
        }
    }

    private fun onUpdateLoadingState(isLoading: Boolean) {
        viewModelState.update { it.copy(isLoading = isLoading) }
    }

    private fun onUpdateErrorState(isError: Boolean) {
        viewModelState.update { it.copy(isError = isError) }
    }

    private fun onUpdateAvailableCarRides(availableCarRides: List<AvailableCarRide>) {
        viewModelState.update { it.copy(availableCarRideList = availableCarRides) }
    }
}