package com.app.comu_carona.feature.home.steps.profile.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.app.comu_carona.commons.usecase.LogoutUseCase
import com.app.comu_carona.feature.home.steps.profile.domain.GetProfileUseCase
import com.app.comu_carona.feature.home.steps.profile.ui.ProfileViewModelEventState.OnLoadProfile
import com.app.comu_carona.feature.home.steps.profile.ui.ProfileViewModelEventState.OnLogout
import com.app.comu_carona.feature.home.steps.profile.ui.ProfileViewModelEventState.OnNavigateToProfileDetails
import com.app.comu_carona.feature.registeraccount.data.models.RegisterAccountRequest
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
class ProfileViewModel(
    private val navController: NavController,
    private val logoutUseCase: LogoutUseCase,
    private val getProfileUseCase: GetProfileUseCase
) : ViewModel() {
    private val viewModelState = MutableStateFlow(ProfileViewModelState())

    init {
        onFetchProfile()
    }

    val uiState = viewModelState
        .map(ProfileViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    fun onEvent(event: ProfileViewModelEventState) {
        when (event) {
            is OnLoadProfile -> onFetchProfile()
            is OnLogout -> handleLogout()
            is OnNavigateToProfileDetails -> onNavigateToProfileDetails()
        }
    }

    private fun onNavigateToProfileDetails() {
        val profileInformation = checkNotNull(viewModelState.value.profileInformation)
        val route = Routes.ProfileDetails.route
        val routeWithId = route.apply {
            replace("{username}", profileInformation.fullName)
            replace("{birthDate}", profileInformation.birthDate)
            replace("{photoUrl}", profileInformation.photoUrl)
        }

        navController.navigate(routeWithId)
    }

    private fun onFetchProfile() {
        onUpdateLoading(true)

        viewModelScope.launch {
            val result = getProfileUseCase()

            result.fold(
                onSuccess = { profileInformation ->
                    onUpdateLoading(false)
                    onUpdateProfile(profileInformation)
                },
                onFailure = { throwable ->
                    val errorCode = (throwable as HttpException).code()

                    when (errorCode) {
                        NetworkingHttpState.UNAUTHORIZED.code -> {
                            handleLogout()
                        }

                        else -> {
                            onUpdateLoading(false)
                            onUpdateError(true)
                        }
                    }
                }
            )
        }
    }

    private fun handleLogout() {
        logoutUseCase(
            navController = navController,
            actualRoute = Routes.Home.route
        )
    }

    private fun onUpdateProfile(userInformation: RegisterAccountRequest) {
        viewModelState.update { it.copy(profileInformation = userInformation) }
    }

    private fun onUpdateLoading(isLoading: Boolean) {
        viewModelState.update { it.copy(isLoading = isLoading) }
    }

    private fun onUpdateError(isError: Boolean) {
        viewModelState.update { it.copy(isError = isError) }
    }
}