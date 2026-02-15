package com.mkulikov.taskb.home.screen.start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkulikov.taskb.navigation.IAppNavigator
import com.mkulikov.taskb.task_api.navigation.TaskList
import kotlinx.coroutines.launch

class HomeViewModel(
    private val appNavigator: IAppNavigator,
): ViewModel() {

    fun handleIntent(intent: StartIntent) {
        viewModelScope.launch {
            when (intent) {
                is StartIntent.OpenTask -> {
                    appNavigator.push(TaskList)
                }
            }
        }
    }
}

sealed class StartIntent {

    data object OpenTask : StartIntent()
}