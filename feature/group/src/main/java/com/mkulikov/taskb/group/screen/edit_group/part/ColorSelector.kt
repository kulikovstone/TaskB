package com.mkulikov.taskb.group.screen.edit_group.part

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.mkulikov.taskb.designsystem.color.GroupColor
import com.mkulikov.taskb.designsystem.theme.TaskBTheme
import com.mkulikov.taskb.designsystem.theme.grey900

@Composable
fun ColorSelector(
    name: String,
    color: Color,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val containerColor = if (selected) color else Color.Transparent
    val contentColor = if (selected) grey900 else MaterialTheme.colorScheme.onSurface
    val borderColor = if (selected) Color.Transparent else MaterialTheme.colorScheme.outlineVariant

    Surface(
        selected = selected,
        onClick = onClick,
        shape = CircleShape,
        color = containerColor,
        contentColor = contentColor,
        border = BorderStroke(1.dp, borderColor),
        modifier = Modifier.height(40.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 10.dp, end = 12.dp),
        ) {
            if (selected) {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(1.dp)
                        .size(18.dp),
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .clip(CircleShape)
                        .background(color),
                )
            }

            Spacer(Modifier.width(8.dp))

            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun ColorSelectorPreview(
) = TaskBTheme {
    Surface {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            ColorSelector(
                name = stringResource(GroupColor.LightGreen.colorNameRes),
                color = GroupColor.LightGreen.color,
                selected = false,
                onClick = {},
            )
            Spacer(modifier = Modifier.size(10.dp))

            ColorSelector(
                name = stringResource(GroupColor.LightGreen.colorNameRes),
                color = GroupColor.LightGreen.color,
                selected = true,
                onClick = {},
            )
        }
    }
}