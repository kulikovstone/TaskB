package com.mkulikov.taskb.task_api.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface TaskRoute : NavKey {

    @Serializable
    data object TaskList : TaskRoute
}