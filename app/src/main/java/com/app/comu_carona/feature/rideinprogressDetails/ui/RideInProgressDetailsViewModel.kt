package com.app.comu_carona.feature.rideinprogressDetails.ui

import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.app.comu_carona.commons.usecase.CallPhoneUseCase
import com.app.comu_carona.commons.usecase.CallWhatsappUseCase
import com.app.comu_carona.commons.usecase.CallWhatsappUseCase.Companion.DEFAULT_MESSAGE_CAR_RIDE
import com.app.comu_carona.commons.usecase.LogoutUseCase
import com.app.comu_carona.commons.usecase.ShareLinkUseCase
import com.app.comu_carona.components.snackbar.SnackbarCustomType
import com.app.comu_carona.components.snackbar.SnackbarCustomType.ERROR
import com.app.comu_carona.feature.carridedetails.data.models.CarRideDetails
import com.app.comu_carona.feature.carridedetails.domain.GetCarRideDetailsUseCase
import com.app.comu_carona.feature.rideinprogressDetails.domain.DeleteReservationsUseCase
import com.app.comu_carona.feature.rideinprogressDetails.ui.RideInProgressDetailsViewModelEventState.OnBack
import com.app.comu_carona.feature.rideinprogressDetails.ui.RideInProgressDetailsViewModelEventState.OnCallPhone
import com.app.comu_carona.feature.rideinprogressDetails.ui.RideInProgressDetailsViewModelEventState.OnCallWhatsApp
import com.app.comu_carona.feature.rideinprogressDetails.ui.RideInProgressDetailsViewModelEventState.OnCancelMyRide
import com.app.comu_carona.feature.rideinprogressDetails.ui.RideInProgressDetailsViewModelEventState.OnDismissBottomSheet
import com.app.comu_carona.feature.rideinprogressDetails.ui.RideInProgressDetailsViewModelEventState.OnGoToHome
import com.app.comu_carona.feature.rideinprogressDetails.ui.RideInProgressDetailsViewModelEventState.OnOpenBottomSheet
import com.app.comu_carona.feature.rideinprogressDetails.ui.RideInProgressDetailsViewModelEventState.OnOpenCancelBottomSheet
import com.app.comu_carona.feature.rideinprogressDetails.ui.RideInProgressDetailsViewModelEventState.OnOpenShare
import com.app.comu_carona.feature.rideinprogressDetails.ui.RideInProgressDetailsViewModelEventState.OnRetry
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
class RideInProgressDetailsViewModel(
    private val riderId: String,
    private val navController: NavController,
    private val snackbarHostState: SnackbarHostState,
    private val getCarRideDetailsUseCase: GetCarRideDetailsUseCase,
    private val deleteReservationsUseCase: DeleteReservationsUseCase,
    private val callWhatsappUseCase: CallWhatsappUseCase,
    private val callPhoneUseCase: CallPhoneUseCase,
    private val shareLinkUseCase: ShareLinkUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {
    private val fullSeatsMessage = "Todas as vagas dessa carona ja foram preechidas! \uD83D\uDE25"
    private val viewModelState = MutableStateFlow(RideInProgressDetailsViewModelState())

    val uiState = viewModelState
        .map(RideInProgressDetailsViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    init {
        onFetchReservationRide(riderId)
    }

    fun onEvent(event: RideInProgressDetailsViewModelEventState) {
        when (event) {
            is OnCancelMyRide -> onFetchCancelMyRide()
            is OnDismissBottomSheet -> onDismissBottomSheet()
            is OnOpenBottomSheet -> onOpenBottomSheet()
            is OnOpenCancelBottomSheet -> onOpenCancelBottomSheet()
            is OnCallWhatsApp -> onCallWhatsApp()
            is OnCallPhone -> onCallPhone()
            is OnGoToHome -> onGoToHome()
            is OnRetry -> onFetchReservationRide(riderId)
            is OnOpenShare -> onOpenShareLink()
            is OnBack -> navController.popBackStack()
        }
    }

    private fun onFetchReservationRide(id: String) {
        onUpdateError(false)
        onUpdateLoading(true)

        viewModelScope.launch {
            getCarRideDetailsUseCase.invoke(id)
                .onSuccess { result ->
                    onUpdateLoading(false)
                    onUpdateCarRideDetails(result)
                }
                .onFailure { throwable ->
                    val errorCode = (throwable as HttpException).code()

                    when (errorCode) {
                        NetworkingHttpState.UNAUTHORIZED.code -> {
                            logoutUseCase(navController, Routes.Home.route)
                        }

                        else -> {
                            onUpdateLoading(false)
                            onUpdateError(true)
                        }
                    }
                }
        }
    }

    private fun onFetchCancelMyRide() {
        onUpdateLoadingReservation(true)

        viewModelScope.launch {
            deleteReservationsUseCase.invoke(riderId)
                .onSuccess {
                    onUpdateLoadingReservation(false)
                    onUpdateSuccessReservation(true)
                }
                .onFailure { throwable ->
                    val errorCode = (throwable as HttpException).code()

                    when (errorCode) {
                        NetworkingHttpState.UNAUTHORIZED.code -> {
                            logoutUseCase(
                                navController = navController,
                                actualRoute = Routes.RideInProgressDetails.route
                            )
                        }

                        else -> {
                            onUpdateLoadingReservation(false)
                            onUpdateShowSnackBar(
                                showSnackBar = true,
                                snackBarMessage = throwable.message ?: "",
                                snackbarType = ERROR
                            )
                        }
                    }
                }
        }
    }

    private fun onUpdateCarRideDetails(carRideDetails: CarRideDetails) {
        viewModelState.update { it.copy(carRideDetailsResponse = carRideDetails) }
    }

    private fun onUpdateShowSnackBar(
        showSnackBar: Boolean,
        snackBarMessage: String,
        snackbarType: SnackbarCustomType
    ) {
        viewModelState.update {
            it.copy(
                showSnackBar = showSnackBar,
                snackbarType = snackbarType
            )
        }

        viewModelScope.launch {
            snackbarHostState.showSnackbar(snackBarMessage)
        }
    }

    private fun onDismissBottomSheet() {
        viewModelState.update { it.copy(showBottomSheet = false, showCancelBottomSheet = false) }
    }

    private fun onOpenBottomSheet() {
        viewModelState.update { it.copy(showBottomSheet = true) }
    }

    private fun onOpenCancelBottomSheet() {
        viewModelState.update { it.copy(showCancelBottomSheet = true) }
    }

    private fun onCallPhone() {
        val data = checkNotNull(viewModelState.value.carRideDetailsResponse)
        callPhoneUseCase(
            phoneNumber = data.bottomSheetCarRideUser.bottomSheetRiderPhoneNumber,
            onErrorAction = { errorMessage ->
                onUpdateShowSnackBar(
                    showSnackBar = true,
                    snackBarMessage = errorMessage,
                    snackbarType = ERROR
                )
            }
        )
    }

    private fun onCallWhatsApp() {
        val data = checkNotNull(viewModelState.value.carRideDetailsResponse)
        callWhatsappUseCase(
            phoneNumber = data.bottomSheetCarRideUser.bottomSheetRiderPhoneNumber,
            message = DEFAULT_MESSAGE_CAR_RIDE
        )
    }

    private fun onGoToHome() {
        navController.navigate(Routes.Home.route) {
            popUpTo(Routes.Home.route) {
                inclusive = true
            }
        }
    }

    private fun onOpenShareLink() {
        val data = checkNotNull(viewModelState.value.carRideDetailsResponse)
        shareLinkUseCase(
            link = data.shareDeeplink,
            onErrorAction = {
                onUpdateShowSnackBar(
                    showSnackBar = true,
                    snackBarMessage = it,
                    snackbarType = ERROR
                )
            })
    }

    private fun onUpdateSuccessReservation(isSuccess: Boolean) {
        viewModelState.update { it.copy(isSuccessReservation = isSuccess) }
    }

    private fun onUpdateLoadingReservation(isLoading: Boolean) {
        viewModelState.update { it.copy(isLoadingReservation = isLoading) }
    }

    private fun onUpdateLoading(isLoading: Boolean) {
        viewModelState.update { it.copy(isLoading = isLoading) }
    }

    private fun onUpdateError(error: Boolean) {
        viewModelState.update { it.copy(isError = error) }
    }

    private fun onUpdateIsEnableButton(isEnableButton: Boolean) {
        viewModelState.update { it.copy(isEnableButton = isEnableButton) }
    }

}