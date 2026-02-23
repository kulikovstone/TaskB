package com.mkulikov.taskb.designsystem.old.part

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun NameField(
    placeholder: String,
    maxLength: Int,
    defaultName: String = "",
    focusRequester: FocusRequester,
    onDone: (String) -> Unit,
) {
    var name by remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier
            .focusRequester(focusRequester)
            .height(56.dp), // Иначе прыгает высота при вводе (не из-за trailingIcon)
        value = name,
        onValueChange = {
            if (it.length <= maxLength) name = it
            else it.take(maxLength)
        },
        singleLine = true,
        placeholder = {
            Text(placeholder)
        },
        trailingIcon = {
            if (name.isNotEmpty()) {
                IconButton(
                    modifier = Modifier.size(30.dp),
                    onClick = {
                        name = ""
                        focusRequester.requestFocus()
                    },
                ) {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        imageVector = Icons.Outlined.Clear,
                        contentDescription = null,
                    )
                }
            }
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = { onDone(name) }
        )
    )

    LaunchedEffect(Unit) {
        name = defaultName
        focusRequester.requestFocus()
    }
}