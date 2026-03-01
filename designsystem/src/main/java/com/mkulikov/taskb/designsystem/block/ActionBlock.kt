package com.mkulikov.taskb.designsystem.block

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ActionBlock(
    content: @Composable () -> Unit,
) {
    Spacer(modifier = Modifier.height(30.dp))

    Text(
        text = "Action",
        style = MaterialTheme.typography.titleMedium
    )

    Spacer(modifier = Modifier.height(10.dp))

    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        content()
    }
}