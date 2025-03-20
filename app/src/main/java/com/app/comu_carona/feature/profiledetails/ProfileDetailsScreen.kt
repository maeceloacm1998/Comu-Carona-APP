package com.app.comu_carona.feature.profiledetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.comu_carona.R
import com.app.comu_carona.components.button.CCButton
import com.app.comu_carona.components.button.CCButtonBack
import com.app.comu_carona.components.horizontalline.HorizontalLine
import com.app.comu_carona.components.permissions.RequestGalleryPermission
import com.app.comu_carona.components.photoselect.PhotoUrlComponent
import com.app.comu_carona.components.snackbar.CCSnackbar
import com.app.comu_carona.components.snackbar.SnackbarCustomType
import com.app.comu_carona.components.textfield.CCTextField
import com.app.comu_carona.feature.profiledetails.ProfileDetailsViewModelEventState.OnBack
import com.app.comu_carona.feature.profiledetails.ProfileDetailsViewModelEventState.OnOpenPhoto
import com.app.comu_carona.feature.profiledetails.ProfileDetailsViewModelEventState.OnUpdateBirthDate
import com.app.comu_carona.feature.profiledetails.ProfileDetailsViewModelEventState.OnUpdateFullName
import com.app.comu_carona.feature.profiledetails.ProfileDetailsViewModelEventState.OnUpdatePhoneNumber
import com.app.comu_carona.feature.profiledetails.ProfileDetailsViewModelEventState.OnUpdateProfile
import com.app.comu_carona.feature.registeraccount.data.models.RegisterAccountRequest

@Composable
fun ProfileDetailsScreen(
    uiState: ProfileDetailsViewModelUiState.HasProfileDetails,
    onEvent: (ProfileDetailsViewModelEventState) -> Unit,
    snackbarHostState: SnackbarHostState
) {
    val profileDetailsInformation = checkNotNull(uiState.profileDetailsInformation)

    Scaffold(
        snackbarHost = {
            CCSnackbar(
                snackbarHostState = snackbarHostState,
                snackbarType = uiState.snackbarType
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .weight(2f)
                    .verticalScroll(rememberScrollState())
                    .background(White)
                    .padding(20.dp)
            ) {

                Spacer(modifier = Modifier.height(20.dp))

                CCButtonBack(onClick = {
                    onEvent(OnBack)
                })

                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = CenterHorizontally
                ) {
                    PhotoUrlComponent(
                        photoUrl = profileDetailsInformation.photoUrl,
                        sizeOfImageBox = 200,
                        isLoading = uiState.isLoadingImage,
                        onPhotoSelected = { uri ->
                            if (uri != null) {
                                onEvent(OnOpenPhoto(uri))
                            }
                        }
                    )

                    RequestGalleryPermission(
                        onPermissionDenied = { },
                        onPermissionGranted = { }
                    )
                }

                Spacer(modifier = Modifier.padding(vertical = 30.dp))

                CCTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    placeholder = stringResource(id = R.string.register_account_stage_of_full_name_hint),
                    value = uiState.profileDetailsInformation.fullName,
                    onValueChange = { text ->
                        onEvent(OnUpdateFullName(fullName = text))
                    },
                    keyboardType = KeyboardType.Text,
                    isErrorMessage = false,
                    onImeAction = {}
                )

                Spacer(modifier = Modifier.padding(vertical = 10.dp))

                CCTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    placeholder = stringResource(id = R.string.register_account_stage_of_birth_date_hint),
                    value = uiState.profileDetailsInformation.birthDate,
                    onValueChange = { text ->
                        onEvent(OnUpdateBirthDate(birthDate = text))
                    },
                    keyboardType = KeyboardType.Text,
                    isErrorMessage = false,
                    onImeAction = {}
                )

                Spacer(modifier = Modifier.padding(vertical = 10.dp))

                CCTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    placeholder = stringResource(id = R.string.register_account_stage_of_phone_number_hint),
                    value = uiState.profileDetailsInformation.phoneNumber,
                    onValueChange = { text ->
                        onEvent(OnUpdatePhoneNumber(phoneNumber = text))
                    },
                    keyboardType = KeyboardType.Text,
                    isErrorMessage = false,
                    onImeAction = {}
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(White)
                    .padding(horizontal = 20.dp)
            ) {
                HorizontalLine(Modifier.padding(bottom = 10.dp))

                CCButton(
                    Modifier.padding(bottom = 20.dp),
                    title = stringResource(R.string.profile_button_title),
                    isLoading = uiState.isLoadingUpdate,
                    isSuccess = uiState.isSuccessUpdate,
                    isEnable = uiState.isChangeFields,
                    onButtonListener = { onEvent(OnUpdateProfile) }
                )
            }
        }
    }
}

@Preview
@Composable
fun ProfileDetailsScreenPreview() {
    ProfileDetailsScreen(
        uiState = ProfileDetailsViewModelUiState.HasProfileDetails(
            profileDetailsInformation = RegisterAccountRequest(
                fullName = "",
                birthDate = "",
                phoneNumber = "",
                photoUrl = ""
            ),
            snackbarType = SnackbarCustomType.ERROR,
            isChangeFields = false,
            isSuccessUpdate = false,
            isLoadingImage = false,
            isLoadingUpdate = false,
        ),
        onEvent = {},
        snackbarHostState = SnackbarHostState()
    )
}