package com.mkulikov.taskb.group.screen.edit_group.part

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.mkulikov.taskb.designsystem.color.StatisticsColor
import com.mkulikov.taskb.designsystem.theme.TaskBTheme

@Composable
internal fun StatisticsItem(
    text: String,
    color: Color,
) = Surface {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .padding(8.dp)
                .size(8.dp)
                .clip(CircleShape)
                .background(color),
        )

        Text(
            text = text,
            modifier = Modifier.padding(bottom = 1.dp),
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@PreviewLightDark
@Composable
private fun StatisticsItemPreview() = TaskBTheme {
    StatisticsItem(
        text = "12 active tasks",
        color = StatisticsColor.Active.color,
    )
}