package com.mkulikov.taskb.group.screen.group_list.part

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.mkulikov.taskb.designsystem.color.GroupColor
import com.mkulikov.taskb.designsystem.theme.TaskBTheme

@Composable
fun GroupItem(
    name: String,
    groupColor: GroupColor,
    openGroup: () -> Unit,
) = Surface(
    modifier = Modifier
        .fillMaxWidth(),
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = openGroup),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .defaultMinSize(minHeight = 60.dp)
                .width(10.dp)
                .background(groupColor.color),
        )
        Text(
            modifier = Modifier
                .padding(start = 15.dp),
            text = name,
        )
    }
}

@PreviewLightDark
@Composable
private fun GroupItemPreview() {
    TaskBTheme {
        GroupItem(
            name = "TaskB",
            groupColor = GroupColor.Blue,
            openGroup = {},
        )
    }
}