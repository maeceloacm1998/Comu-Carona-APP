package com.app.comu_carona.feature.home.steps.myrideinprogress.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.app.comu_carona.feature.home.steps.myrideinprogress.domain.GetMyRideInProgressUseCase
import com.app.comu_carona.feature.home.steps.rideinprogress.data.models.RideInProgressFilterOptions
import com.app.comu_carona.feature.home.steps.rideinprogress.data.models.RideInProgressModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class MyRideInProgressViewModel(
    private val navController: NavController,
    private val getMyRideInProgressUseCase: GetMyRideInProgressUseCase
) : ViewModel() {
    private val viewModelState =
        MutableStateFlow(
            MyRideInProgressViewModelState(
                rideInProgressFilterSelected = RideInProgressFilterOptions.TODOS
            )
        )

    init {
        onLoadAvailableCarRide()
    }

    val uiState = viewModelState
        .map(MyRideInProgressViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    fun onEvent(event: MyRideInProgressViewModelEventState) {
        when (event) {
            is MyRideInProgressViewModelEventState.OnLoadMyRideInProgress -> onLoadAvailableCarRide()
            is MyRideInProgressViewModelEventState.OnSelectFilter -> onSelectFilter(event.rideInProgressFilterOptions)
            is MyRideInProgressViewModelEventState.OnNavigateTo -> onNavigateTo(event.route)
        }
    }

    private fun onLoadAvailableCarRide() {
        onUpdateLoading(true)

        viewModelScope.launch {
            val status = viewModelState.value.rideInProgressFilterSelected.toString()

            onUpdateLoading(false)

            getMyRideInProgressUseCase.invoke(status)
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

    private fun onSelectFilter(rideInProgressFilterOptions: RideInProgressFilterOptions) {
        onUpdateFilterSelected(rideInProgressFilterOptions)
        onLoadAvailableCarRide()
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