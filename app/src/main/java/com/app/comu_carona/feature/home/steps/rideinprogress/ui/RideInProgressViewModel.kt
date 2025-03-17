package com.app.comu_carona.feature.home.steps.rideinprogress.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.app.comu_carona.commons.usecase.LogoutUseCase
import com.app.comu_carona.feature.home.steps.rideinprogress.data.models.RideInProgressFilterOptions
import com.app.comu_carona.feature.home.steps.rideinprogress.data.models.RideInProgressModel
import com.app.comu_carona.feature.home.steps.rideinprogress.domain.GetRideInProgressUseCase
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
class RideInProgressViewModel(
    private val navController: NavController,
    private val getRideInProgressUseCase: GetRideInProgressUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {
    private val viewModelState =
        MutableStateFlow(
            RideInProgressViewModelState(
                rideInProgressFilterSelected = RideInProgressFilterOptions.ALL
            )
        )

    init {
        onLoadAvailableCarRide()
    }

    val uiState = viewModelState
        .map(RideInProgressViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    fun onEvent(event: RideInProgressViewModelEventState) {
        when (event) {
            is RideInProgressViewModelEventState.OnLoadRideInProgress -> onLoadAvailableCarRide()
            is RideInProgressViewModelEventState.OnSelectFilter -> onSelectFilter(event.rideInProgressFilterOptions)
            is RideInProgressViewModelEventState.OnNavigateTo -> onNavigateTo(
                event.route,
                event.params
            )
        }
    }

    private fun onLoadAvailableCarRide() {
        onUpdateLoading(true)

        viewModelScope.launch {
            val status = viewModelState.value.rideInProgressFilterSelected.toString()

            onUpdateLoading(false)

            getRideInProgressUseCase.invoke(status)
                .onSuccess { result ->
                    onUpdateRideInProgressList(result)
                    onUpdateLoading(false)
                }
                .onFailure { throwable ->
                    val errorCode = (throwable as HttpException).code()

                    when (errorCode) {
                        NetworkingHttpState.UNAUTHORIZED.code -> {
                            logoutUseCase(
                                navController = navController,
                                actualRoute = Routes.Home.route
                            )
                        }

                        else -> {
                            onUpdateError(true)
                            onUpdateLoading(false)
                        }
                    }
                }
        }
    }

    private fun onSelectFilter(rideInProgressFilterOptions: RideInProgressFilterOptions) {
        onUpdateFilterSelected(rideInProgressFilterOptions)
        onLoadAvailableCarRide()
    }

    private fun onNavigateTo(route: String, param: String) {
        val routeWithId = route.replace("{id}", param)
        navController.navigate(routeWithId)
    }

    private fun onUpdateRideInProgressList(rideInProgressList: List<RideInProgressModel>) {
        viewModelState.update {
            it.copy(
                rideInProgressList = rideInProgressList,
                rideInProgressListFiltered = rideInProgressList
            )
        }
    }

    private fun onUpdateFilterSelected(rideInProgressFilterOptions: RideInProgressFilterOptions) {
        viewModelState.update {
            it.copy(rideInProgressFilterSelected = rideInProgressFilterOptions)
        }
    }

    private fun onUpdateLoading(isLoading: Boolean) {
        viewModelState.update { it.copy(isLoading = isLoading) }
    }

    private fun onUpdateError(error: Boolean) {
        viewModelState.update { it.copy(isError = error) }
    }
}