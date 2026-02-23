package com.mkulikov.taskb.task.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.mkulikov.taskb.core.navigation.IAppNavigator
import com.mkulikov.taskb.core.navigation.INavEntry
import com.mkulikov.taskb.task.screen.task_list.TaskListScreen
import com.mkulikov.taskb.task.screen.task_list.TaskListViewModel
import com.mkulikov.taskb.task_api.navigation.TaskRoute
import org.koin.androidx.compose.koinViewModel

class TaskNavEntry() : INavEntry {

    override fun register(scope: EntryProviderScope<NavKey>, navigator: IAppNavigator) =
        with(scope) {
            entry<TaskRoute.TaskList> {
                val viewModel = koinViewModel<TaskListViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()

                TaskListScreen(
                    state = state,
                    intent = viewModel::handleIntent,
                )
            }
        }
}