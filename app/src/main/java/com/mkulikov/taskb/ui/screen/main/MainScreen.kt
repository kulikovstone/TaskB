package com.mkulikov.taskb.ui.screen.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mkulikov.taskb.database.Group
import com.mkulikov.taskb.database.Task
import com.mkulikov.taskb.ui.part.GroupList
import com.mkulikov.taskb.ui.part.TaskList
import com.mkulikov.taskb.ui.screen.loading.LoadingScreen

@Composable
fun MainScreen(
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











