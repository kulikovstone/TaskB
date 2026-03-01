package com.mkulikov.taskb.designsystem.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable

@Composable
fun ConfirmationDialog(
    confirmation: Confirmation,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = confirmation.title,
                style = MaterialTheme.typography.titleMedium
            )
        },
        text = {
            Text(
                text = confirmation.text,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        confirmButton = {
            TextButton(
                onClick = onConfirm,
            ) {
                Text(confirmation.confirmText ?: "OK")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
            ) {
                Text(confirmation.dismissText ?: "Cancel")
            }
        }
    )
}

@Immutable
data class Confirmation(
    val title: String,
    val text: String,
    val confirmText: String? = null,
    val dismissText: String? = null,
)