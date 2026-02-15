package com.mkulikov.taskb.designsystem.util

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mkulikov.taskb.designsystem.theme.TaskBTheme

@Composable
fun PreviewContainer(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) = TaskBTheme {
    Surface(
        color = MaterialTheme.colorScheme.background,
    ) {
        Box (
            modifier = modifier,
        ) {
            content()
        }
    }
}