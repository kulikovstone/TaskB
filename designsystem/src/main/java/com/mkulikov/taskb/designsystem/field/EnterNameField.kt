package com.mkulikov.taskb.designsystem.field

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.mkulikov.taskb.designsystem.theme.TaskBTheme

@Composable
fun EnterNameField(
    name: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    onClear: () -> Unit,
) {
    OutlinedTextField(
        value = name,
        onValueChange = { onValueChange(it) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        placeholder = {
            Text(placeholder)
        },
        trailingIcon = {
            if (name.isNotEmpty()) {
                IconButton(
                    onClick = onClear,
                ) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = "Clear"
                    )
                }
            }
        }
    )
}

@PreviewLightDark
@Composable
private fun EnterNameFieldPreview() = TaskBTheme {
    Surface {
        EnterNameField(
            name = "Home work",
            placeholder = "Enter group name",
            onValueChange = {},
            onClear = {},
        )
    }
}