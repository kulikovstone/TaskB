package com.mkulikov.taskb.task.screen.task_list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.FilterAltOff
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.mkulikov.taskb.core.designsystem.component.TaskItemNew
import com.mkulikov.taskb.designsystem.screen.loading.LoadingScreen

@Composable
fun TaskListScreen(
    state: TaskListState,
    intent: (TaskListIntent) -> Unit,
) {

    when (state) {
        is TaskListState.Loading -> LoadingScreen()
        is TaskListState.Data -> {
            TaskListContent(
                title = state.title,
                filtered = state.filtered,
                taskList = state.tasks,
                intent = intent,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TaskListContent(
    title: String,
    filtered: Boolean,
    taskList: List<Task>,
    intent: (TaskListIntent) -> Unit,
) {
    Scaffold(
        contentWindowInsets = WindowInsets.statusBars,
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { intent(TaskListIntent.OpenSettings) }) {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = "Settings",
                        )
                    }
                },
                title = {
                    Text(
                        text = title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
                actions = {
                    IconButton(onClick = { intent(TaskListIntent.OpenFilters) }) {
                        Icon(
                            imageVector = Icons.Filled.FilterAlt,
                            contentDescription = "Filters",
                        )
                    }
                    if (filtered) {
                        IconButton(onClick = { intent(TaskListIntent.ClearFilters) }) {
                            Icon(
                                imageVector = Icons.Filled.FilterAltOff,
                                contentDescription = "Clear filters",
                            )
                        }
                    }
                    IconButton(onClick = { intent(TaskListIntent.AddTask) }) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Add",
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            contentPadding = PaddingValues(bottom = 100.dp),
        ) {
            items(
                items = taskList,
                key = { it.id },
            ) { task ->
                TaskItemNew(
                    name = task.name,
                    color = task.color,
                    isCompleted = task.isCompleted,
                    changeStatusCompletion = { intent(TaskListIntent.ChangeTaskCompletion(task)) },
                    openTask = { intent(TaskListIntent.OpenTask(task)) },
                )
            }
        }
    }
}

