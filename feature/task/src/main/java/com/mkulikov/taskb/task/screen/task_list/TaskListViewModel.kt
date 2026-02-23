package com.mkulikov.taskb.task.screen.task_list

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.mkulikov.taskb.core.navigation.IAppNavigator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random

class TaskListViewModel(
    val appNavigator: IAppNavigator,
) : ViewModel() {

    val state = MutableStateFlow<TaskListState>(TaskListState.Loading)

    init {
        state.update { getTaskListMock() }
    }

    fun handleIntent(intent: TaskListIntent) {
        when (intent) {
            TaskListIntent.Back -> appNavigator.pop()
            TaskListIntent.AddTask -> Log.w("!!!", "AddTask")
            is TaskListIntent.ChangeTaskCompletion -> Log.w("!!!", "ChangeTaskCompletion")
            TaskListIntent.ClearFilters -> Log.w("!!!", "ClearFilters")
            TaskListIntent.OpenFilters -> Log.w("!!!", "OpenFilters")
            TaskListIntent.OpenSettings -> Log.w("!!!", "OpenSettings")
            is TaskListIntent.OpenTask -> Log.w("!!!", "OpenTask")
        }
    }
}

sealed class TaskListState {

    data object Loading : TaskListState()
    data class Data(
        val title: String,
        val filtered: Boolean,
        val tasks: List<Task>,
    ) : TaskListState()
}

sealed class TaskListIntent {

    data object Back : TaskListIntent()
    data object OpenSettings : TaskListIntent()
    data object OpenFilters : TaskListIntent()
    data object ClearFilters : TaskListIntent()
    data object AddTask : TaskListIntent()
    data class OpenTask(val task: Task) : TaskListIntent()
    data class ChangeTaskCompletion(val task: Task) : TaskListIntent()
}