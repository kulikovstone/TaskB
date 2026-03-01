package com.mkulikov.taskb.designsystem.button

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.mkulikov.taskb.designsystem.theme.TaskBTheme

@Composable
fun ActionButton(
    label: String,
    imageVector: ImageVector,
    onClick: () -> Unit,
) {
    FilledTonalButton(
        contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
        onClick = onClick,
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            modifier = Modifier.size(ButtonDefaults.IconSize),
        )
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
        )
    }
}

@PreviewLightDark
@Composable
private fun ActionButtonPreview() = TaskBTheme {
    ActionButton(
        label = "Delete",
        imageVector = Icons.Outlined.Delete,
        onClick = {},
    )
}