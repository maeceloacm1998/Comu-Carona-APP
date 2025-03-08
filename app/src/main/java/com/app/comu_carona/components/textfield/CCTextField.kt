package com.app.comu_carona.components.textfield

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.comu_carona.theme.Error
import com.app.comu_carona.theme.GrayLight
import com.app.comu_carona.theme.TextFieldColor
import com.app.comu_carona.theme.TextFieldLineColor
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CCTextField(
    modifier: Modifier = Modifier,
    placeholder: String = "placeholder",
    placeholderSize: Int = 16,
    textAlign: TextAlign = TextAlign.Start,
    value: String,
    onValueChange: (String) -> Unit = {},
    onDebouncedChange: (String) -> Unit = {},
    debounceTime: Long = 800L,
    keyboardType: KeyboardType = KeyboardType.Number,
    maxLines: Int = 1,
    maxLength: Int = 100,
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
    var textFieldValue by remember { mutableStateOf(TextFieldValue(text = value)) }
    val coroutineScope = rememberCoroutineScope()
    var debounceJob by remember { mutableStateOf<Job?>(null) }

    LaunchedEffect(value) {
        if (textFieldValue.text != value) {
            textFieldValue = textFieldValue.copy(
                text = value,
                selection = TextRange(value.length)
            )
        }
    }

    LaunchedEffect(textFieldValue.text) {
        debounceJob?.cancel()
        debounceJob = coroutineScope.launch {
            delay(debounceTime)
            onDebouncedChange(textFieldValue.text)
        }
    }

    OutlinedTextField(
        value = textFieldValue,
        onValueChange = {
            if (it.text.length <= maxLength) {
                textFieldValue = it
                onValueChange(it.text)
            }
        },
        modifier = modifier,
        placeholder = {
            Text(
                text = placeholder,
                fontSize = placeholderSize.sp,
                textAlign = textAlign,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = GrayLight,
            unfocusedContainerColor = GrayLight,
            focusedTextColor = textColor,
            unfocusedTextColor = textColor,
            focusedPlaceholderColor = textColor,
            unfocusedPlaceholderColor = textColor,
            cursorColor = TextFieldColor,
            focusedIndicatorColor = borderColor,
            unfocusedIndicatorColor = borderColor
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
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            fontSize = placeholderSize.sp,
            color = textColor,
            textAlign = textAlign
        ),
        singleLine = isSingleLine,
        maxLines = maxLines,
        shape = RoundedCornerShape(10.dp),
    )
}

@Preview
@Composable
fun CCTextFieldPreview() {
    CCTextField(
        value = "",
        onValueChange = {},
        onImeAction = {},
        onDebouncedChange = {}
    )
}