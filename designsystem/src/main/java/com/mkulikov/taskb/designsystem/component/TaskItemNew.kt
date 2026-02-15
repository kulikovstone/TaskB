package com.mkulikov.taskb.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mkulikov.taskb.designsystem.theme.TaskBTheme
import com.mkulikov.taskb.core.designsystem.theme.lightGreen400

@Composable
fun TaskItemNew(
    name: String,
    color: Color?,
    isCompleted: Boolean,
    changeStatusCompletion: () -> Unit,
    openTask: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(6.dp)
                .background(color ?: Color.Transparent)
        )
        Checkbox(
            checked = isCompleted,
            onCheckedChange = { changeStatusCompletion() },
        )
        Text(
            text = name,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = openTask),
            color = if (isCompleted) Color.Gray else Color.Black,
            textDecoration = if (isCompleted) TextDecoration.LineThrough else TextDecoration.None,
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun TaskItemUnfinishedPreview() {
    TaskBTheme {
        TaskItemNew(
            name = "Погладить кота",
            color = lightGreen400,
            isCompleted = false,
            changeStatusCompletion = {},
            openTask = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TaskItemFinishedPreview() {
    TaskBTheme {
        TaskItemNew(
            name = "Погладить кота",
            color = lightGreen400,
            isCompleted = true,
            changeStatusCompletion = {},
            openTask = {},
        )
    }
}