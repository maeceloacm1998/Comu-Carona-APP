package com.app.comu_carona.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HomeViewModel: ViewModel() {
    private val viewModelState = MutableStateFlow(HomeViewModelState())

    val uiState = viewModelState
        .map(HomeViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    fun onEvent(event: HomeViewModelEventState) {
        when(event) {
            is HomeViewModelEventState.OnLoadAvailableCarRide -> onLoadAvailableCarRide()
        }
    }

    private fun onLoadAvailableCarRide() {

    }
}