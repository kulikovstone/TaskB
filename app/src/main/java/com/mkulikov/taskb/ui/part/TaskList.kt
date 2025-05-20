package com.mkulikov.taskb.ui.part

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mkulikov.taskb.database.Group
import com.mkulikov.taskb.database.Task
import com.mkulikov.taskb.ext.isCompleted
import com.mkulikov.taskb.ui.element.AddNameField
import com.mkulikov.taskb.ui.element.TaskItem
import com.mkulikov.taskb.ui.screen.main.MainIntent

@Composable
fun TaskList(
    tasks: List<Task>,
    selectedTask: Task?,
    selectedGroup: Group?,
    intent: (MainIntent) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        AddNameField(
            placeholder = "Название задачи",
            buttonName = "Добавить",
            add = { text ->
                intent(MainIntent.AddTask(text, selectedGroup?.id))
            }
        )

        if (tasks.isEmpty()) {
            EmptyTaskList()
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(items = tasks, key = { it.id }) { task ->
                    TaskItem(
                        name = task.name,
                        isCompleted = task.isCompleted(),
                        changeStatusCompletion = { intent(MainIntent.ChangeTaskCompletion(task)) },
                        isSelected = selectedTask?.id == task.id,
                        select = { intent(MainIntent.SelectTask(task)) },
                        remove = { intent(MainIntent.RemoveTask(task)) }
                    )
                }
            }
        }
    }
}

@Composable
fun EmptyTaskList() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Нет задач",
            modifier = Modifier.padding(16.dp)
        )
    }
}