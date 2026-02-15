package com.mkulikov.taskb.core.designsystem.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mkulikov.taskb.designsystem.theme.TaskBTheme

@Composable
fun EmailField(
    modifier: Modifier = Modifier,
    value: String,
    onChange: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current

    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onChange,
        leadingIcon = {
            Icon(
                Icons.Default.Email,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.primary
            )
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        ),
        placeholder = {
            Text("Enter your email")
        },
        label = {
            Text("Email")
        },
        singleLine = true,
    )
}

@Preview(showBackground = true)
@Composable
private fun EmailFieldPreview() = TaskBTheme {
    EmailField(
        modifier = Modifier.padding(16.dp),
        value = "",
        onChange = {},
    )
}