package com.app.comu_carona.feature.home.steps.profile.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.comu_carona.R
import com.app.comu_carona.components.boxselection.CCBoxSelection
import com.app.comu_carona.components.carridecard.UserSelectionBox
import com.app.comu_carona.components.horizontalline.HorizontalLine
import com.app.comu_carona.feature.home.steps.profile.ui.ProfileViewModelEventState.OnLogout
import com.app.comu_carona.feature.home.steps.profile.ui.ProfileViewModelEventState.OnNavigateToProfileDetails
import com.app.comu_carona.feature.registeraccount.data.models.RegisterAccountRequest
import com.app.comu_carona.theme.SoftBlack
import com.app.comu_carona.theme.TextFieldLineColor

@Composable
fun ProfileScreen(
    uiState: ProfileViewModelUiState.HasProfile,
    onEvent: (ProfileViewModelEventState) -> Unit
) {
    val profile: RegisterAccountRequest = checkNotNull(uiState.profileInformation)

    Scaffold(
        topBar = {
            ProfileTopBar(
                modifier = Modifier
                    .background(White)
                    .padding(vertical = 20.dp),
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(White)
        ) {
            HorizontalLine(
                modifier = Modifier
                    .padding(
                        horizontal = 10.dp,
                    ),
                color = TextFieldLineColor,
                thickness = 1f
            )

            Spacer(modifier = Modifier.padding(vertical = 10.dp))

            UserSelectionBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                riderPhotoUrl = profile.photoUrl,
                riderUserName = profile.fullName,
                riderDescription = "Participa de alvo da mocidade",
                showArrow = false
            )

            Spacer(modifier = Modifier.padding(vertical = 10.dp))

            CCBoxSelection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                title = stringResource(R.string.profile_information_title),
                description = stringResource(R.string.profile_information_description),
                iconPainter = painterResource(R.drawable.ic_profile),
                onClick = {
                    onEvent(OnNavigateToProfileDetails)
                }
            )

            Spacer(modifier = Modifier.padding(top = 5.dp))

            CCBoxSelection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                title = stringResource(R.string.profile_exit_app_title),
                description = stringResource(R.string.profile_exit_app_description),
                iconImageVector = Icons.Outlined.ExitToApp,
                onClick = {
                    onEvent(OnLogout)
                }
            )
        }
    }
}

@Composable
fun ProfileTopBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.profile_title),
            style = MaterialTheme.typography.titleMedium,
            color = SoftBlack,
            fontWeight = SemiBold,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(
        uiState = ProfileViewModelUiState.HasProfile(
            profileInformation = RegisterAccountRequest(
                fullName = "John Doe",
                photoUrl = "https://randomuser.me/api/portraits",
                phoneNumber = "(31) 99999-9999",
                birthDate = "02/05/2025"
            ),
            isLoading = false,
            isError = false
        ),
        onEvent = {}
    )
}