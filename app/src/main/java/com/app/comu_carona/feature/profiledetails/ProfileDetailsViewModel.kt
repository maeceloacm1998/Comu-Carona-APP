package com.app.comu_carona.feature.profiledetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.app.comu_carona.feature.profiledetails.ProfileDetailsViewModelEventState.OnBack
import com.app.comu_carona.feature.registeraccount.data.models.RegisterAccountRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class ProfileDetailsViewModel(
    private val navController: NavController,
    private val userName : String,
    private val birthDate : String,
    private val phoneNumber : String,
    private val photoUrl : String,
) : ViewModel() {
    private val viewModelState = MutableStateFlow(ProfileDetailsViewModelState())

    init {
        onUpdateProfileDetails()
    }

    val uiState = viewModelState
        .map(ProfileDetailsViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    fun onEvent(event: ProfileDetailsViewModelEventState) {
        when (event) {
            is OnBack -> navController.popBackStack()
        }
    }

    private fun onUpdateProfileDetails() {
        viewModelState.update {
            it.copy(
                profileDetailsInformation = RegisterAccountRequest(
                    fullName = userName,
                    birthDate = birthDate,
                    phoneNumber = phoneNumber,
                    photoUrl = photoUrl
                )
            )
        }
    }
}