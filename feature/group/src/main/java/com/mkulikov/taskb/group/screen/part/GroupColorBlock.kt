package com.mkulikov.taskb.group.screen.part

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mkulikov.taskb.designsystem.color.GroupColor
import com.mkulikov.taskb.group.screen.edit_group.part.ColorSelector

@Composable
internal fun GroupColorBlock(
    selectedColor: GroupColor,
    onClick: (GroupColor) -> Unit,
) {
    Spacer(modifier = Modifier.height(30.dp))

    Text(
        text = "Color",
        style = MaterialTheme.typography.titleMedium
    )

    Spacer(modifier = Modifier.height(10.dp))

    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        GroupColor.getAllColors().forEach { groupColor ->
            ColorSelector(
                name = stringResource(groupColor.colorNameRes),
                color = groupColor.color,
                selected = groupColor == selectedColor,
                onClick = { onClick(groupColor) },
            )
        }
    }
}