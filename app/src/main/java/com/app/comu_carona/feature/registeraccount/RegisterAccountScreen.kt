package com.app.comu_carona.feature.registeraccount

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.comu_carona.components.button.CCButton
import com.app.comu_carona.components.button.CCButtonBack
import com.app.comu_carona.components.permissions.RequestGalleryPermission
import com.app.comu_carona.components.textfield.CCTextField
import com.app.comu_carona.feature.registeraccount.RegisterAccountViewModelEventState.*
import com.app.comu_carona.feature.registeraccount.data.models.RegisterAccountSteps.BIRTH_DATE
import com.app.comu_carona.feature.registeraccount.data.models.RegisterAccountSteps.FULL_NAME
import com.app.comu_carona.feature.registeraccount.data.models.RegisterAccountSteps.PHONE_NUMBER
import com.app.comu_carona.feature.registeraccount.data.models.RegisterAccountSteps.PHOTO
import com.app.comu_carona.theme.GrayLight
import com.app.comu_carona.theme.SoftBlack
import com.app.comu_carona.theme.TextFieldColor
import com.app.comu_carona.theme.TextFieldLineColor
import com.app.comu_carona.utils.StringUtils.BIRTH_DATE_LENGTH
import com.app.comu_carona.utils.StringUtils.FULL_NAME_LENGTH
import com.app.comu_carona.utils.StringUtils.PHONE_NUMBER_LENGTH
import com.app.comu_carona.utils.StringUtils.formatBirthDate
import com.app.comu_carona.utils.StringUtils.formatPhoneNumber

@Composable
fun StageOfFullNameScreen(
    uiState: RegisterAccountViewModelUiState.Register,
    event: (RegisterAccountViewModelEventState) -> Unit
) {
    val focusRequesters = FocusRequester()
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        focusRequesters.requestFocus()
        keyboardController?.show()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(20.dp)
    ) {

        Spacer(modifier = Modifier.height(90.dp))

        Text(
            text = "Como podemos\nte chamar?",
            style = MaterialTheme.typography.titleLarge,
            color = SoftBlack
        )

        Spacer(modifier = Modifier.height(7.dp))

        Text(
            text = "Esse nome será exibido para todas as pessoas que você solicitar carona.",
            style = MaterialTheme.typography.bodyLarge,
            color = TextFieldColor
        )

        Spacer(modifier = Modifier.height(30.dp))

        CCTextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequesters),
            placeholder = "Nome completo",
            value = uiState.fullName,
            onValueChange = { text ->
                event(OnUpdateFullName(text))
            },
            keyboardType = KeyboardType.Text,
            isErrorMessage = false,
            onImeAction = {
                event(OnNextStep(FULL_NAME))
            }
        )

        Spacer(modifier = Modifier.height(30.dp))

        CCButton(
            modifier = Modifier.fillMaxWidth(),
            title = "Continuar",
            isEnable = uiState.fullName.length >= FULL_NAME_LENGTH,
            onButtonListener = {
                event(OnNextStep(FULL_NAME))
            }
        )
    }
}

@Composable
fun StageOfBirthDateScreen(
    uiState: RegisterAccountViewModelUiState.Register,
    event: (RegisterAccountViewModelEventState) -> Unit
) {
    val focusRequesters = FocusRequester()
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        focusRequesters.requestFocus()
        keyboardController?.show()
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(20.dp)
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        CCButtonBack(onClick = {
            event(OnRemoveNewStep(BIRTH_DATE))
        })

        Spacer(modifier = Modifier.height(27.dp))

        Text(
            text = "Qual a sua idade?",
            style = MaterialTheme.typography.titleLarge,
            color = SoftBlack
        )

        Spacer(modifier = Modifier.height(7.dp))

        Text(
            text = "Saber sua idade é importânte para o motorista da carona ter mais detalhes sobre você!",
            style = MaterialTheme.typography.bodyLarge,
            color = TextFieldColor
        )

        Spacer(modifier = Modifier.height(30.dp))

        CCTextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequesters),
            placeholder = "Digite sua data de nascimento",
            value = uiState.birthDate.formatBirthDate(),
            onValueChange = { text ->
                event(OnUpdateBirthDate(text))
            },
            maxLength = BIRTH_DATE_LENGTH,
            keyboardType = KeyboardType.Number,
            isErrorMessage = false,
            onImeAction = {
                event(OnNextStep(BIRTH_DATE))
            }
        )

        Spacer(modifier = Modifier.height(30.dp))

        CCButton(
            modifier = Modifier.fillMaxWidth(),
            title = "Continuar",
            isEnable = uiState.birthDate.formatBirthDate().length == BIRTH_DATE_LENGTH,
            onButtonListener = {
                event(OnNextStep(BIRTH_DATE))
            }
        )
    }
}

@Composable
fun StageOfPhoneNumberScreen(
    uiState: RegisterAccountViewModelUiState.Register,
    event: (RegisterAccountViewModelEventState) -> Unit
) {
    val focusRequesters = FocusRequester()
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        focusRequesters.requestFocus()
        keyboardController?.show()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(20.dp)
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        CCButtonBack(onClick = {
            event(OnRemoveNewStep(PHONE_NUMBER))
        })

        Spacer(modifier = Modifier.height(27.dp))

        Text(
            text = "Informe seu telefone",
            style = MaterialTheme.typography.titleLarge,
            color = SoftBlack
        )

        Spacer(modifier = Modifier.height(7.dp))

        Text(
            text = "Seu número de contato é importânte para caso o motorista da carona precise entrar em contato. \uD83D\uDE01",
            style = MaterialTheme.typography.bodyLarge,
            color = TextFieldColor
        )

        Spacer(modifier = Modifier.height(30.dp))

        CCTextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequesters),
            placeholder = "Ex: 31999999999",
            value = uiState.phoneNumber.formatPhoneNumber(),
            onValueChange = { text ->
                event(OnUpdatePhoneNumber(text))
            },
            maxLength = PHONE_NUMBER_LENGTH,
            keyboardType = KeyboardType.Number,
            isErrorMessage = false,
            onImeAction = {
                event(OnNextStep(PHONE_NUMBER))
            }
        )

        Spacer(modifier = Modifier.height(30.dp))

        CCButton(
            modifier = Modifier.fillMaxWidth(),
            title = "Continuar",
            isEnable = uiState.phoneNumber.formatPhoneNumber().length == PHONE_NUMBER_LENGTH,
            onButtonListener = {
                event(OnNextStep(PHONE_NUMBER))
            }
        )
    }
}

@Composable
fun StageOfPhotoScreen(
    uiState: RegisterAccountViewModelUiState,
    event: (RegisterAccountViewModelEventState) -> Unit
) {
    RequestGalleryPermission(
        onPermissionDenied = { event(OnGrantedPermission(false)) },
        onPermissionGranted = { event(OnGrantedPermission(true)) }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(20.dp),
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        CCButtonBack(onClick = {
            event(OnRemoveNewStep(PHOTO))
        })

        Spacer(modifier = Modifier.height(27.dp))

        Text(
            text = "Estamos no final",
            style = MaterialTheme.typography.titleLarge,
            color = SoftBlack
        )

        Spacer(modifier = Modifier.height(7.dp))

        Text(
            text = "Por último, coloque uma foto que seu rosto esteja visível, assim fica mais fácil de te indentificar! \uD83E\uDD78",
            style = MaterialTheme.typography.bodyLarge,
            color = TextFieldColor
        )

        Spacer(modifier = Modifier.height(30.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = CenterHorizontally
        ) {
            PhotoComponent()
        }

        Spacer(modifier = Modifier.height(30.dp))

        CCButton(
            modifier = Modifier.fillMaxWidth(),
            title = "Finalizar",
            onButtonListener = {
                event(OnNextStep(PHOTO))
            }
        )
    }
}

@Composable
fun PhotoComponent(
    onClickPhoto: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .size(300.dp)
            .background(GrayLight, shape = RoundedCornerShape(500.dp))
            .clickable { onClickPhoto() }
            .border(1.dp, TextFieldLineColor, shape = RoundedCornerShape(500.dp)),
        verticalArrangement = Center,
        horizontalAlignment = CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(70.dp),
            imageVector = Icons.TwoTone.Person,
            contentDescription = null,
            tint = TextFieldColor
        )

        Text(
            text = "Selecionar foto",
            style = MaterialTheme.typography.bodyLarge,
            color = TextFieldColor
        )
    }
}

@Preview
@Composable
fun StageOfFullNameScreenPreview() {
    StageOfFullNameScreen(
        uiState = RegisterAccountViewModelUiState.Register(
            steps = FULL_NAME,
            fullName = "John Doe",
            birthDate = "01/01/2000",
            phoneNumber = "31999999999",
            isLoading = false,
            isError = false,
            isSuccess = false
        ),
        event = { }
    )
}

@Preview
@Composable
fun StageOfBirthDateScreenPreview() {
    StageOfBirthDateScreen(
        uiState = RegisterAccountViewModelUiState.Register(
            steps = BIRTH_DATE,
            fullName = "John Doe",
            birthDate = "01/01/2000",
            phoneNumber = "31999999999",
            isLoading = false,
            isError = false,
            isSuccess = false
        ),
        event = { }
    )
}

@Preview
@Composable
fun StageOfPhoneNumberScreenPreview() {
    StageOfPhoneNumberScreen(
        uiState = RegisterAccountViewModelUiState.Register(
            steps = PHONE_NUMBER,
            fullName = "John Doe",
            birthDate = "01/01/2000",
            phoneNumber = "31999999999",
            isLoading = false,
            isError = false,
            isSuccess = false
        ),
        event = { }
    )
}

@Preview
@Composable
fun StageOfPhotoScreenPreview() {
    StageOfPhotoScreen(
        uiState = RegisterAccountViewModelUiState.Register(
            steps = PHOTO,
            fullName = "John Doe",
            birthDate = "01/01/2000",
            phoneNumber = "31999999999",
            isLoading = false,
            isError = false,
            isSuccess = false
        ),
        event = { }
    )
}