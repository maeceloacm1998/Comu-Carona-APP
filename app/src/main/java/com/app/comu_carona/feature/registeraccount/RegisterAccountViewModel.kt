package com.app.comu_carona.feature.registeraccount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class RegisterAccountViewModel: ViewModel() {
    private val viewModelState = MutableStateFlow(RegisterAccountViewModelState(isLoading = false))

    val uiState = viewModelState
        .map(RegisterAccountViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )
}