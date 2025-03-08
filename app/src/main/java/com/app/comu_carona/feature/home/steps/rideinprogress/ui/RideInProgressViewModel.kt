package com.app.comu_carona.feature.home.steps.rideinprogress.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.app.comu_carona.feature.home.steps.rideinprogress.data.models.RideInProgressModel
import com.app.comu_carona.feature.home.steps.rideinprogress.domain.GetRideInProgressUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class RideInProgressViewModel(
    private val navController: NavController,
    private val getRideInProgressUseCase: GetRideInProgressUseCase
) : ViewModel() {
    private val viewModelState = MutableStateFlow(RideInProgressViewModelState())

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
            is RideInProgressViewModelEventState.OnNavigateTo -> onNavigateTo(event.route)
        }
    }

    private fun onLoadAvailableCarRide() {
        onUpdateLoading(true)

        viewModelScope.launch {
            val status = viewModelState.value.rideInProgressFilterSelected

            onUpdateLoading(false)

            getRideInProgressUseCase.invoke(status)
                .onSuccess { result ->
                    onUpdateRideInProgressList(result)
                    onUpdateLoading(false)
                }
                .onFailure {
                    onUpdateError(true)
                    onUpdateLoading(false)
                }
        }
    }

    private fun onNavigateTo(route: String) {
        navController.navigate(route)
    }

    private fun onUpdateRideInProgressList(rideInProgressList: List<RideInProgressModel>) {
        viewModelState.update {
            it.copy(
                rideInProgressList = rideInProgressList,
                rideInProgressListFiltered = rideInProgressList
            )
        }
    }

    private fun onUpdateLoading(isLoading: Boolean) {
        viewModelState.update { it.copy(isLoading = isLoading) }
    }

    private fun onUpdateSuccess(isSuccess: Boolean) {
        viewModelState.update { it.copy(isSuccess = isSuccess) }
    }

    private fun onUpdateError(error: Boolean) {
        viewModelState.update { it.copy(isError = error) }
    }
}