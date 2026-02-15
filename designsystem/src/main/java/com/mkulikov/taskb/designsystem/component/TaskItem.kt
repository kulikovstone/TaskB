package com.mkulikov.taskb.core.designsystem.component

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material.icons.outlined.PriorityHigh
import androidx.compose.material.icons.outlined.Today
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mkulikov.taskb.designsystem.theme.TaskBTheme

@Composable
fun TaskItem(
    name: String,
    isCompleted: Boolean,
    changeStatusCompletion: () -> Unit,
    isSelected: Boolean,
    select: () -> Unit,
    remove: () -> Unit,
) {
    if (isSelected && !isCompleted) {
        Surface {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .padding(horizontal = 16.dp)
                        .clickable(onClick = select),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = isCompleted,
                        onCheckedChange = { changeStatusCompletion() },
                        modifier = Modifier.padding(end = 16.dp)
                    )

                    Text(
                        text = name,
                        modifier = Modifier.weight(1f),
                        color = if (isCompleted) Color.Gray else Color.Black,
                        textDecoration = if (isCompleted) TextDecoration.LineThrough else TextDecoration.None,
                    )
                }

                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                ) {
                    ActionButton(
                        imageVector = Icons.Outlined.Edit,
                        onClick = {},
                    )

                    ActionButton(
                        imageVector = Icons.Outlined.Group,
                        onClick = {},
                    )

                    ActionButton(
                        imageVector = Icons.Outlined.PriorityHigh,
                        onClick = {},
                    )

                    ActionButton(
                        imageVector = Icons.Outlined.Today,
                        onClick = {},
                    )

                    ActionButton(
                        imageVector = Icons.Outlined.Delete,
                        onClick = remove,
                    )
                }
            }
        }
    } else {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .padding(horizontal = 16.dp)
                .clickable(onClick = select),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isCompleted,
                onCheckedChange = { changeStatusCompletion() },
                modifier = Modifier.padding(end = 16.dp)
            )

            Text(
                text = name,
                modifier = Modifier.weight(1f),
                color = if (isCompleted) Color.Gray else Color.Black,
                textDecoration = if (isCompleted) TextDecoration.LineThrough else TextDecoration.None,
            )
        }
    }
}

@Preview(name = "TaskItem - Default", showBackground = true)
@Composable
private fun TaskItemPreview_Default() {
    MaterialTheme {
        TaskItem(
            name = "Купить молоко",
            isCompleted = false,
            changeStatusCompletion = {},
            isSelected = false,
            select = {},
            remove = {},
        )
    }
}

@Preview(name = "TaskItem - Completed", showBackground = true)
@Composable
private fun TaskItemPreview_Completed() {
    MaterialTheme {
        TaskItem(
            name = "Сходить в зал",
            isCompleted = true,
            changeStatusCompletion = {},
            isSelected = false,
            select = {},
            remove = {},
        )
    }
}

@Preview(name = "TaskItem - Selected", showBackground = true)
@Composable
private fun TaskItemPreview_Selected() {
    TaskBTheme {
        TaskItem(
            name = "Сделать превью для TaskItem",
            isCompleted = false,
            changeStatusCompletion = {},
            isSelected = true,
            select = {},
            remove = {},
        )
    }
}

@Preview(
    name = "TaskItem - Selected (Dark)",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun TaskItemPreview_Selected_Dark() {
    MaterialTheme {
        TaskItem(
            name = "Темная тема",
            isCompleted = false,
            changeStatusCompletion = {},
            isSelected = true,
            select = {},
            remove = {},
        )
    }
}