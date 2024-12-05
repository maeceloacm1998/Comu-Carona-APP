package com.app.comu_carona.feature.registeraccount

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.comu_carona.components.button.CCButton
import com.app.comu_carona.components.button.CCButtonBack
import com.app.comu_carona.components.textfield.CCTextField
import com.app.comu_carona.theme.SoftBlack
import com.app.comu_carona.theme.TextFieldColor
import com.app.comu_carona.utils.StringUtils.formatBirthDate
import com.app.comu_carona.utils.StringUtils.formatPhoneNumber

@Composable
fun StageOfFullNameScreen() {
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
            modifier = Modifier.fillMaxWidth(),
            placeholder = "Nome completo",
            value = "",
            onValueChange = { },
            keyboardType = KeyboardType.Text,
            isErrorMessage = false,
            onImeAction = { }
        )

        Spacer(modifier = Modifier.height(30.dp))

        CCButton(
            modifier = Modifier.fillMaxWidth(),
            title = "Continuar",
            onButtonListener = { }
        )
    }
}

@Composable
fun StageOfBirthDateScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(20.dp)
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        CCButtonBack(onClick = { })

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
            modifier = Modifier.fillMaxWidth(),
            placeholder = "Digite sua data de nascimento",
            value = "02051998".formatBirthDate(),
            onValueChange = { },
            keyboardType = KeyboardType.Number,
            isErrorMessage = false,
            onImeAction = { }
        )

        Spacer(modifier = Modifier.height(30.dp))

        CCButton(
            modifier = Modifier.fillMaxWidth(),
            title = "Continuar",
            onButtonListener = { }
        )
    }
}

@Composable
fun StageOfPhoneNumberScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(20.dp)
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        CCButtonBack(onClick = { })

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
            modifier = Modifier.fillMaxWidth(),
            placeholder = "Ex: 31999999999",
            value = "31999999999".formatPhoneNumber(),
            onValueChange = { },
            keyboardType = KeyboardType.Number,
            isErrorMessage = false,
            onImeAction = { }
        )

        Spacer(modifier = Modifier.height(30.dp))

        CCButton(
            modifier = Modifier.fillMaxWidth(),
            title = "Continuar",
            onButtonListener = { }
        )
    }
}

@Preview
@Composable
fun StageOfFullNameScreenPreview() {
    StageOfFullNameScreen()
}

@Preview
@Composable
fun StageOfBirthDateScreenPreview() {
    StageOfBirthDateScreen()
}

@Preview
@Composable
fun StageOfPhoneNumberScreenPreview() {
    StageOfPhoneNumberScreen()
}