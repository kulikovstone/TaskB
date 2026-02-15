package com.mkulikov.taskb.task.screen_old.task.task_list

import androidx.lifecycle.ViewModel

class TaskListViewModel(

): ViewModel() {

}

sealed class TaskListState {

    data object Loading : TaskListState()
    data object Data : TaskListState()
    data object Error : TaskListState()
}

sealed class TaskListIntent {

    data class Login(val email: String, val password: String) : TaskListIntent()
    data object Registration : TaskListIntent()
    data object Remember : TaskListIntent()
}