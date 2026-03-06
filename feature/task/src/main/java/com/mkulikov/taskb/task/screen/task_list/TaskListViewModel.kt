package com.mkulikov.taskb.task.screen.task_list

import android.util.Log
import androidx.lifecycle.ViewModel
import com.mkulikov.taskb.core.navigation.IAppNavigator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class TaskListViewModel(
    val appNavigator: IAppNavigator,
) : ViewModel() {

    private val _state = MutableStateFlow<TaskListState>(TaskListState.Loading)
    val state = _state.asStateFlow()

    init {
        _state.update { getTaskListMock() }
    }

    fun handleIntent(intent: TaskListIntent) {
        when (intent) {
            TaskListIntent.Back -> appNavigator.back()
            TaskListIntent.AddTask -> Log.w("!!!", "AddTask")
            is TaskListIntent.ChangeTaskCompletion -> Log.w("!!!", "ChangeTaskCompletion")
            TaskListIntent.ClearFilters -> Log.w("!!!", "ClearFilters")
            TaskListIntent.OpenFilters -> Log.w("!!!", "OpenFilters")
            TaskListIntent.OpenSettings -> Log.w("!!!", "OpenSettings")
            is TaskListIntent.OpenTask -> Log.w("!!!", "OpenTask")
        }
    }
}

internal sealed interface TaskListState {

    data object Loading : TaskListState
    data class Data(
        val title: String,
        val filtered: Boolean,
        val tasks: List<Task>,
    ) : TaskListState
}

internal sealed interface TaskListIntent {

    data object Back : TaskListIntent
    data object OpenSettings : TaskListIntent
    data object OpenFilters : TaskListIntent
    data object ClearFilters : TaskListIntent
    data object AddTask : TaskListIntent
    data class OpenTask(val task: Task) : TaskListIntent
    data class ChangeTaskCompletion(val task: Task) : TaskListIntent
}