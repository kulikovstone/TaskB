package com.mkulikov.taskb.task.screen.task_list

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class TaskListViewModel : ViewModel() {

    val state = MutableStateFlow<TaskListState>(
        taskListMock()
    )

    fun handleIntent(intent: TaskListIntent) {
        when(intent){
            TaskListIntent.AddTask -> Log.w("!!!", "AddTask")
            is TaskListIntent.ChangeTaskCompletion -> Log.w("!!!", "ChangeTaskCompletion")
            TaskListIntent.ClearFilters -> Log.w("!!!", "ClearFilters")
            TaskListIntent.OpenFilters -> Log.w("!!!", "OpenFilters")
            TaskListIntent.OpenSettings -> Log.w("!!!", "OpenSettings")
            is TaskListIntent.OpenTask -> Log.w("!!!", "OpenTask")
        }
    }
}

