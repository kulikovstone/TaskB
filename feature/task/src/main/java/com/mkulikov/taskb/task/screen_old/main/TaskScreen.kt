package com.mkulikov.taskb.task.screen_old.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mkulikov.taskb.designsystem.screen.loading.LoadingScreen
import com.mkulikov.taskb.feature.task_data.database.Group
import com.mkulikov.taskb.feature.task_data.database.Task
import com.mkulikov.taskb.task.screen_old.GroupList
import com.mkulikov.taskb.task.screen_old.TaskList

@Composable
fun TaskScreen(
    state: MainState,
    intent: (MainIntent) -> Unit,
) {
    when (state) {
        is MainState.Loading -> LoadingScreen()
        is MainState.Data -> {
            Content(
                groups = state.groups,
                tasks = state.tasks,
                selectedGroup = state.selected.group,
                selectedTask = state.selected.task,
                counts = state.counts,
                filter = state.filter,
                intent = intent,
            )
        }
    }
}

@Composable
fun Content(
    tasks: List<Task>,
    groups: List<Group>,
    selectedGroup: Group?,
    selectedTask: Task?,
    counts: Counts,
    intent: (MainIntent) -> Unit,
    filter: TaskFilter,
) {
    Row {
        Box(
            modifier = Modifier.width(280.dp)
        ) {
            GroupList(
                groups = groups,
                intent = intent,
                counts = counts,
                filter = filter,
                selectedGroup = selectedGroup,
            )
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            TaskList(
                tasks = tasks,
                selectedTask = selectedTask,
                selectedGroup = selectedGroup,
                intent = intent,
            )
        }
    }
}











