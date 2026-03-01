package com.mkulikov.taskb.task.screen.task_list.part

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.mkulikov.taskb.designsystem.color.GroupColor
import com.mkulikov.taskb.designsystem.theme.TaskBTheme
import kotlin.random.Random

@Composable
fun TaskItem(
    name: String,
    color: Color?,
    isCompleted: Boolean,
    changeStatusCompletion: () -> Unit,
    openTask: () -> Unit,
) = Surface {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = openTask),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .defaultMinSize(minHeight = 60.dp)
                .width(10.dp)
                .background(color ?: Color.Transparent)
        )
        Checkbox(
            checked = isCompleted,
            onCheckedChange = { changeStatusCompletion() },
        )
        Text(
            text = name,
            modifier = Modifier
                .fillMaxWidth(),
            textDecoration = if (isCompleted) TextDecoration.LineThrough else TextDecoration.None,
        )
    }

}

@PreviewLightDark
@Composable
private fun TaskItemPreview() {
    TaskBTheme {
        TaskItem(
            name = "Погладить кота",
            color = GroupColor.Green.color,
            isCompleted = Random.nextBoolean(),
            changeStatusCompletion = {},
            openTask = {},
        )
    }
}