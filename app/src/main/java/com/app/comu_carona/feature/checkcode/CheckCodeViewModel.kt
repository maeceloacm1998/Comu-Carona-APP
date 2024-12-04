package com.app.comu_carona.feature.checkcode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class CheckCodeViewModel: ViewModel() {
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
            is CheckCodeViewModelEventState.OnChangedCode -> {
                viewModelState.value = viewModelState.value.copy(
                    messageList = event.code
                )
            }

            is CheckCodeViewModelEventState.OnClickCheckCode -> {
                viewModelState.value = viewModelState.value.copy(
                    isLoading = true
                )
            }
        }
    }


}