package com.mkulikov.taskb.home.screen.start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkulikov.taskb.core.navigation.IAppNavigator
import com.mkulikov.taskb.group_api.navigation.GroupRoute
import com.mkulikov.taskb.task_api.navigation.TaskRoute
import kotlinx.coroutines.launch

class HomeViewModel(
    private val appNavigator: IAppNavigator,
) : ViewModel() {

    fun handleIntent(intent: StartIntent) {
        viewModelScope.launch {
            when (intent) {
                StartIntent.OpenGroups -> {
                    appNavigator.push(GroupRoute.GroupList)
                }

                StartIntent.OpenTasks -> {
                    appNavigator.push(TaskRoute.TaskList)
                }
            }
        }
    }
}

sealed class StartIntent {

    data object OpenGroups : StartIntent()
    data object OpenTasks : StartIntent()
}