package com.app.comu_carona.feature.checkcode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class CheckCodeViewModel : ViewModel() {
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
            viewModelState.update { it.copy(isLoading = true) }
        }
    }
}