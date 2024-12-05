package com.app.comu_carona.components.textfield

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.comu_carona.theme.Error
import com.app.comu_carona.theme.GrayLight
import com.app.comu_carona.theme.TextFieldColor
import com.app.comu_carona.theme.TextFieldLineColor

@Composable
fun CCTextField(
    modifier: Modifier = Modifier,
    placeholder: String = "placeholder",
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Number,
    maxLines: Int = 1,
    isSingleLine: Boolean = true,
    isErrorMessage: Boolean = false,
    onImeAction: () -> Unit
) {
    val borderColor: Color = when {
        isErrorMessage -> Error
        else -> TextFieldLineColor
    }

    val textColor: Color = when {
        isErrorMessage -> Error
        else -> TextFieldColor
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = GrayLight,      // Cor do fundo quando focado
            unfocusedContainerColor = GrayLight,    // Cor do fundo quando desfocado
            focusedTextColor = textColor,      // Cor do texto quando focado
            unfocusedTextColor = textColor,    // Cor do texto quando desfocado
            focusedPlaceholderColor = textColor,     // Cor do placeholder quando focado
            unfocusedPlaceholderColor = textColor,  // Cor do placeholder quando desfocado
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
        textStyle = MaterialTheme.typography.bodyMedium,
        singleLine = isSingleLine,
        maxLines = maxLines,
        shape = RoundedCornerShape(10.dp)
    )
}

@Preview
@Composable
fun CCTextFieldPreview() {
    CCTextField(
        value = "",
        onValueChange = {},
        onImeAction = {}
    )
}