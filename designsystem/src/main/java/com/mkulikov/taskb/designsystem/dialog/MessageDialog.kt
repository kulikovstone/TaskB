package com.mkulikov.taskb.designsystem.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable

@Composable
fun MessageDialog(
    message: Message,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = message.title,
                style = MaterialTheme.typography.titleMedium,
            )
        },
        text = {
            Text(
                text = message.text,
                style = MaterialTheme.typography.bodyMedium,
            )
        },
        confirmButton = {
            TextButton(
                onClick = onDismiss,
            ) {
                Text("ОК")
            }
        },
    )
}

@Immutable
data class Message(
    val title: String,
    val text: String,
)