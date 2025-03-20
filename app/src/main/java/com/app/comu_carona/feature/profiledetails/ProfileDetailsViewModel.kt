package com.app.comu_carona.feature.profiledetails

import android.net.Uri
import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.app.comu_carona.commons.usecase.LogoutUseCase
import com.app.comu_carona.components.snackbar.SnackbarCustomType
import com.app.comu_carona.feature.home.steps.initial.domain.GetUserInformationUseCase
import com.app.comu_carona.feature.home.steps.profile.domain.UpdateProfileUseCase
import com.app.comu_carona.feature.profiledetails.ProfileDetailsViewModelEventState.OnBack
import com.app.comu_carona.feature.profiledetails.ProfileDetailsViewModelEventState.OnOpenPhoto
import com.app.comu_carona.feature.profiledetails.ProfileDetailsViewModelEventState.OnUpdateBirthDate
import com.app.comu_carona.feature.profiledetails.ProfileDetailsViewModelEventState.OnUpdateFullName
import com.app.comu_carona.feature.profiledetails.ProfileDetailsViewModelEventState.OnUpdatePhoneNumber
import com.app.comu_carona.feature.profiledetails.ProfileDetailsViewModelEventState.OnUpdateProfile
import com.app.comu_carona.feature.registeraccount.data.models.RegisterAccountRequest
import com.app.comu_carona.feature.registeraccount.domain.UploadPhotoUseCase
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
class ProfileDetailsViewModel(
    private val navController: NavController,
    private val snackbarHostState: SnackbarHostState,
    private val photoUseCase: UploadPhotoUseCase,
    private val updateProfileUseCase: UpdateProfileUseCase,
    private val getUserInformationUseCase: GetUserInformationUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val userName: String,
    private val birthDate: String,
    private val phoneNumber: String,
) : ViewModel() {
    private val viewModelState = MutableStateFlow(ProfileDetailsViewModelState())

    init {
        onUpdateProfileDetails()
        onFetchUserInformation()
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
            is OnOpenPhoto -> onUpdatePhoto(event.uri)
            is OnUpdateFullName -> onUpdateFullName(event.fullName)
            is OnUpdateBirthDate -> onUpdateBirthDate(event.birthDate)
            is OnUpdatePhoneNumber -> onUpdatePhoneNumber(event.phoneNumber)
            is OnUpdateProfile -> onUpdateProfile()
        }
    }

    private fun onUpdatePhoto(uri: Uri) {
        onUpdateLoadingImage(true)

        viewModelScope.launch {
            photoUseCase(uri)
                .onSuccess {
                    onUpdateLoadingImage(false)
                    onUpdatePhotoUrl(it.uri)
                    onUpdateShowSnackBar(
                        snackbarType = SnackbarCustomType.SUCCESS,
                        snackBarMessage = "Foto atualizada com sucesso! 😎\n" +
                                "Feche o aplicativo para atualizar a foto."
                    )
                }
                .onFailure { throwable ->
                    val errorCode = (throwable as HttpException).code()

                    when (errorCode) {
                        NetworkingHttpState.UNAUTHORIZED.code -> {
                            logoutUseCase(navController, Routes.Home.route)
                        }

                        else -> {
                            onUpdateLoadingImage(false)
                            onUpdateShowSnackBar(
                                snackbarType = SnackbarCustomType.ERROR,
                                snackBarMessage = "Erro ao atualizar a foto"
                            )
                        }
                    }
                }
        }
    }

    private fun onUpdateProfile() {
        onUpdateLoadingUpdate(true)

        viewModelScope.launch {
            val profileInformation = checkNotNull(viewModelState.value.profileDetailsInformation)
            updateProfileUseCase(
                userName = profileInformation.fullName,
                birthDate = profileInformation.birthDate,
                phoneNumber = profileInformation.phoneNumber,
            ).onSuccess {
                onUpdateLoadingUpdate(false)
                onUpdateSuccessUpdate(true)
                onUpdateShowSnackBar(
                    snackbarType = SnackbarCustomType.SUCCESS,
                    snackBarMessage = "Perfil atualizado com sucesso! 😎"
                )
            }.onFailure { throwable ->
                val errorCode = (throwable as HttpException).code()

                when (errorCode) {
                    NetworkingHttpState.UNAUTHORIZED.code -> {
                        logoutUseCase(navController, Routes.Home.route)
                    }

                    else -> {
                        onUpdateLoadingUpdate(false)
                        onUpdateShowSnackBar(
                            snackbarType = SnackbarCustomType.ERROR,
                            snackBarMessage = "Erro ao atualizar o perfil. Tente novamente."
                        )
                    }
                }
            }
        }
    }

    private fun onFetchUserInformation() {
        onUpdateLoadingImage(true)

        viewModelScope.launch {
            val result = getUserInformationUseCase()

            result.fold(
                onSuccess = { userInformation ->
                    onUpdateLoadingImage(false)
                    onUpdatePhotoUrl(userInformation.photoUrl)
                },
                onFailure = { throwable ->
                    val errorCode = (throwable as HttpException).code()

                    when (errorCode) {
                        NetworkingHttpState.UNAUTHORIZED.code -> {
                            logoutUseCase(navController, Routes.Home.route)
                        }

                        else -> {
                            onUpdateLoadingImage(false)
                            onUpdateShowSnackBar(
                                snackbarType = SnackbarCustomType.ERROR,
                                snackBarMessage = "Erro ao buscar informações do usuário"
                            )
                        }
                    }
                }
            )
        }
    }

    private fun onUpdateProfileDetails() {
        viewModelState.update {
            it.copy(
                profileDetailsInformation = RegisterAccountRequest(
                    fullName = userName,
                    birthDate = birthDate,
                    phoneNumber = phoneNumber
                )
            )
        }
    }

    private fun onUpdateShowSnackBar(
        snackBarMessage: String,
        snackbarType: SnackbarCustomType
    ) {
        viewModelState.update {
            it.copy(snackbarType = snackbarType)
        }

        viewModelScope.launch {
            snackbarHostState.showSnackbar(snackBarMessage)
        }
    }

    private fun onUpdateFullName(fullName: String) {
        viewModelState.update {
            it.copy(
                profileDetailsInformation = it.profileDetailsInformation?.copy(fullName = fullName)
            )
        }
        onUpdateIsChangeFields(true)
    }

    private fun onUpdateBirthDate(birthDate: String) {
        viewModelState.update {
            it.copy(
                profileDetailsInformation = it.profileDetailsInformation?.copy(birthDate = birthDate)
            )
        }
        onUpdateIsChangeFields(true)
    }

    private fun onUpdatePhoneNumber(phoneNumber: String) {
        viewModelState.update {
            it.copy(
                profileDetailsInformation = it.profileDetailsInformation?.copy(phoneNumber = phoneNumber)
            )
        }
        onUpdateIsChangeFields(true)
    }

    private fun onUpdateIsChangeFields(isChangeFields: Boolean) {
        viewModelState.update {
            it.copy(isChangeFields = isChangeFields)
        }
    }

    private fun onUpdatePhotoUrl(photoUrl: String) {
        viewModelState.update {
            it.copy(
                profileDetailsInformation = it.profileDetailsInformation?.copy(photoUrl = photoUrl)
            )
        }
    }

    private fun onUpdateLoadingImage(isLoading: Boolean) {
        viewModelState.update {
            it.copy(isLoadingImage = isLoading)
        }
    }

    private fun onUpdateLoadingUpdate(isLoading: Boolean) {
        viewModelState.update {
            it.copy(isLoadingUpdate = isLoading)
        }
    }

    private fun onUpdateSuccessUpdate(isSuccess: Boolean) {
        viewModelState.update {
            it.copy(isSuccessUpdate = isSuccess)
        }
    }

}