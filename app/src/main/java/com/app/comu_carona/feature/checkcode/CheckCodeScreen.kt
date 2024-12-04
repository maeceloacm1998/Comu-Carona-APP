package com.app.comu_carona.feature.checkcode

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.comu_carona.R
import com.app.comu_carona.components.button.CCButton
import com.app.comu_carona.theme.Error
import com.app.comu_carona.theme.GrayLight
import com.app.comu_carona.theme.Success
import com.app.comu_carona.theme.TextColor
import com.app.comu_carona.theme.TextFieldColor
import com.app.comu_carona.theme.TextFieldLineColor

@Composable
fun CheckCodeScreen(
    uiState: CheckCodeViewModelUiState.Code,
    onEvent: (CheckCodeViewModelEventState) -> Unit
) {
    val focusRequesters = List(5) { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        focusRequesters[0].requestFocus()
        keyboardController?.show()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Center
    ) {
        Text(
            text = stringResource(R.string.check_code_title),
            style = MaterialTheme.typography.titleMedium,
        )

        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(R.string.check_code_message),
            style = MaterialTheme.typography.bodyMedium,
            color = TextColor,
            textAlign = TextAlign.Center,
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 39.dp, start = 39.dp, end = 39.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = CenterVertically
        ) {
            uiState.code.forEachIndexed { index, value ->
                CodeTextField(
                    modifier = Modifier
                        .width(width = 50.dp)
                        .focusRequester(focusRequesters[index]),
                    value = uiState.code[index],
                    onValueChange = { newValue ->
                        if (newValue.length <= 1) {
                            val updatedValues = uiState.code.toMutableList()
                            updatedValues[index] = newValue
                            onEvent.invoke(CheckCodeViewModelEventState.OnChangedCode(updatedValues))

                            // Focus next field
                            if (newValue.isNotEmpty() && index < focusRequesters.size - 1) {
                                focusRequesters[index + 1].requestFocus()
                            }

                            // Focus previous field
                            if (newValue.isEmpty() && index > 0) {
                                focusRequesters[index - 1].requestFocus()
                            }
                        }
                    },
                    fontSize = 15,
                    isErrorMessage = uiState.isError,
                    isSuccessMessage = uiState.isSuccess,
                    onImeAction = {
                        onEvent.invoke(CheckCodeViewModelEventState.OnClickCheckCode)
                    }
                )
            }
        }

        if (uiState.isError) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Center,
                verticalAlignment = CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Warning,
                    contentDescription = null,
                    tint = Error,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(16.dp)
                )
                Text(
                    text = stringResource(R.string.check_code_error_message),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Error,
                    textAlign = TextAlign.Center,
                )
            }
        }

        CCButton(
            modifier = Modifier.padding(top = 39.dp, start = 20.dp, end = 20.dp),
            title = stringResource(R.string.check_code_button_title),
            isLoading = uiState.isLoading,
            isSuccess = uiState.isSuccess,
            isEnable = uiState.code.all { it.isNotEmpty() },
            onButtonListener = { onEvent.invoke(CheckCodeViewModelEventState.OnClickCheckCode) }
        )
    }
}

@Composable
fun CodeTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    fontSize: Int = 15,
    keyboardType: KeyboardType = KeyboardType.Number,
    isErrorMessage: Boolean = false,
    isSuccessMessage: Boolean = false,
    onImeAction: () -> Unit
) {
    val borderColor: Color = when {
        isErrorMessage -> Error
        isSuccessMessage -> Success
        else -> TextFieldLineColor
    }

    val textColor: Color = when {
        isErrorMessage -> Error
        isSuccessMessage -> Success
        else -> TextFieldColor
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = GrayLight,      // Cor do fundo quando focado
            unfocusedContainerColor = GrayLight,    // Cor do fundo quando desfocado
            focusedTextColor = textColor,      // Cor do texto quando focado
            unfocusedTextColor = textColor,    // Cor do texto quando desfocado
            cursorColor = TextFieldColor,           // Cor do cursor
            focusedIndicatorColor = borderColor, // Cor da borda focada
            unfocusedIndicatorColor = borderColor // Cor da borda desfocada
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = keyboardType,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onImeAction()
                keyboardController?.hide()
            }
        ),
        textStyle = LocalTextStyle.current.copy(
            textAlign = TextAlign.Center,
            fontSize = fontSize.sp,
        ),
        singleLine = true,
        maxLines = 1,
        shape = RoundedCornerShape(10.dp)
    )
}

@Preview
@Composable
fun CheckCodeScreenPreview() {
    CheckCodeScreen(
        uiState = CheckCodeViewModelUiState.Code(
            code = List(5) { "" },
            isLoading = false,
            isError = false,
            isSuccess = false
        ),
        onEvent = {}
    )
}