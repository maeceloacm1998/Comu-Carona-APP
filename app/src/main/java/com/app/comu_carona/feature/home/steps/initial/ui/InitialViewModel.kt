package com.app.comu_carona.feature.home.steps.initial.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.app.comu_carona.commons.usecase.LogoutUseCase
import com.app.comu_carona.feature.home.steps.initial.data.models.AvailableCarRide
import com.app.comu_carona.feature.home.steps.initial.domain.AvailableCarRidesUseCase
import com.app.comu_carona.feature.home.steps.initial.domain.GetUserInformation
import com.app.comu_carona.feature.home.steps.initial.ui.InitialViewModelEventState.OnLoadAvailableCarRide
import com.app.comu_carona.feature.home.steps.initial.ui.InitialViewModelEventState.OnNavigateTo
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
import javax.net.ssl.SSLException

@KoinViewModel
class InitialViewModel(
    private val availableCarRidesUseCase: AvailableCarRidesUseCase,
    private val navController: NavController,
    private val logoutUseCase: LogoutUseCase,
    private val getUserInformation: GetUserInformation
) : ViewModel() {
    private val viewModelState = MutableStateFlow(InitialViewModelState())

    init {
        onLoadUserInformation()
        onLoadAvailableCarRide()
    }

    val uiState = viewModelState
        .map(InitialViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    fun onEvent(event: InitialViewModelEventState) {
        when (event) {
            is OnLoadAvailableCarRide -> onLoadAvailableCarRide()
            is OnNavigateTo -> onNavigateTo(event.route, event.params)
        }
    }

    private fun onLoadUserInformation() {
        viewModelScope.launch {
            val result = getUserInformation()

            result.fold(
                onSuccess = { userInformation ->
                    viewModelState.update { it.copy(
                        userName = userInformation.name,
                        photoUrl = userInformation.photoUrl
                    ) }
                },
                onFailure = { throwable ->
                    val errorCode = (throwable as HttpException).code()

                    when (errorCode) {
                        NetworkingHttpState.UNAUTHORIZED.code -> {
                            logoutUseCase(navController, Routes.Home.route)
                        }
                    }
                }
            )
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
                    when (throwable) {
                        is HttpException -> {
                            val errorCode = throwable.code()
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
                        is SSLException -> {
                            onUpdateLoadingState(false)
                            onUpdateErrorState(true)
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

    private fun onNavigateTo(route: String, param: String) {
        val routeWithId = route.replace("{id}", param)
        navController.navigate(routeWithId)
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