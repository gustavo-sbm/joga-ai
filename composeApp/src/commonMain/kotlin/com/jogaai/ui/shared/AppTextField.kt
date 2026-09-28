package com.jogaai.ui.shared

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    onFocusLost: () -> Unit = {},
    label: String,
    password: Boolean = false,
    errorMessage: String? = null,
    modifier: Modifier = Modifier
) {
    var itHadFocus by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = modifier.onFocusChanged{ focusState ->
            if(focusState.isFocused){
                itHadFocus = true
            } else if(itHadFocus){
                onFocusLost()
            }

        },
        isError = errorMessage != null,
        supportingText = errorMessage?.let { { Text(it)}  },
        visualTransformation = if (password) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        }

    )
}
