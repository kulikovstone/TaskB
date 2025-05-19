package com.mkulikov.taskb.ui.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkulikov.taskb.data.IMainRepository
import com.mkulikov.taskb.database.Group
import com.mkulikov.taskb.database.Task
import com.mkulikov.taskb.ext.isCompleted
import com.mkulikov.taskb.ext.isGroup
import com.mkulikov.taskb.ext.isToday
import com.mkulikov.taskb.ext.isWithoutGroup
import com.mkulikov.taskb.ext.now
import com.mkulikov.taskb.holder.IGroupDataHolder
import com.mkulikov.taskb.holder.ITaskDataHolder
import com.mkulikov.taskb.util.createIf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

class MainViewModel(
    private val repository: IMainRepository,
    private val groupHolder: IGroupDataHolder,
    private val taskHolder: ITaskDataHolder,
) : ViewModel() {

    private val counts = combine(groupHolder.groups, taskHolder.tasks) { groups, tasks ->
        Counts(
            uncompleted = tasks.count { !it.isCompleted() },
            today = tasks.count { it.isToday() },
            withoutGroup = tasks.count { it.isWithoutGroup() },
            group = groups.associate { group ->
                group.id to tasks.count { it.isGroup(group.id) }
            }
        )
    }

    private val filter = MutableStateFlow<TaskFilter>(TaskFilter.Uncompleted)

    private val filteredTasks = combine(taskHolder.tasks, filter) { tasks, filter ->
        tasks.filter(filter).sort()
    }

    private val selected = combine(groupHolder.selected, taskHolder.selected) { group, task ->
        Selected(group, task)
    }

    val state = combine(
        groupHolder.groups,
        filteredTasks,
        selected,
        counts,
        filter,
    ) { groups, tasks, selected, counts, filter ->
        MainState.Data(
            groups = groups.sortedBy { it.name },
            tasks = tasks,
            selected = selected,
            counts = counts,
            filter = filter,
        )
    }.stateIn(viewModelScope, SharingStarted.Lazily, MainState.Loading)

    var toAddGroup: (() -> Unit)? = null
    var toEditGroup: (() -> Unit)? = null

    fun action(action: MainAction) {
        viewModelScope.launch {
            when (action) {
                is MainAction.AddGroup -> toAddGroup?.invoke()
                is MainAction.EditGroup -> toEditGroup?.invoke()

                is MainAction.SelectGroup -> groupSelection(action.group)
                is MainAction.SelectDefaultGroup -> selectDefaultGroup(action.taskFilter)

                is MainAction.AddTask -> addTask(action.name, action.groupId)
                is MainAction.RemoveTask -> removeTask(action.task)
                is MainAction.ChangeTaskCompletion -> changeTaskCompletion(action.task)
                is MainAction.SelectTask -> taskSelection(action.task)
            }
        }
    }

    private suspend fun addTask(name: String, groupId: Int?) {
        taskHolder.unselectTask()
        repository.addTask(
            Task(
                name = name,
                groupId = groupId,
                targetDate = createIf(filter.value == TaskFilter.Today) {
                    LocalDate.now()
                }
            )
        )
    }

    private suspend fun removeTask(task: Task) {
        repository.removeTask(task)
    }

    private suspend fun changeTaskCompletion(task: Task) {
        val isCompleted = !task.isCompleted()
        repository.updateTask(
            task.copy(
                completedAt = if (isCompleted) LocalDateTime.now() else null,
            )
        )
    }

    private fun taskSelection(task: Task) {
        taskHolder.changeSelection(task)
    }

    private fun groupSelection(group: Group) {
        filter.value = TaskFilter.Group(group.id)
        taskHolder.unselectTask()
        groupHolder.selectGroup(group)
    }

    private fun selectDefaultGroup(taskFilter: TaskFilter) {
        filter.value = taskFilter
        groupHolder.unselectGroup()
        taskHolder.unselectTask()
    }

    private fun List<Task>.filter(filter: TaskFilter): List<Task> {
        return filter {
            when (filter) {
                TaskFilter.Uncompleted -> !it.isCompleted()
                TaskFilter.Today -> it.isToday()
                TaskFilter.WithoutGroup -> it.isWithoutGroup()
                TaskFilter.Completed -> it.isCompleted()
                is TaskFilter.Group -> it.isGroup(filter.id)
            }
        }
    }

    private fun List<Task>.sort(): List<Task> {
        return sortedByDescending { it.createdAt }
    }
}

sealed class MainState {

    data object Loading : MainState()
    data class Data(
        val groups: List<Group>,
        val tasks: List<Task>,
        val selected: Selected,
        val counts: Counts,
        val filter: TaskFilter,
    ) : MainState()
}

data class Selected(
    val group: Group? = null,
    val task: Task? = null,
)

sealed class MainAction {

    data object AddGroup : MainAction()
    data class EditGroup(val group: Group) : MainAction()

    data class SelectGroup(val group: Group) : MainAction()
    data class SelectDefaultGroup(val taskFilter: TaskFilter) : MainAction()

    data class AddTask(val name: String, val groupId: Int?) : MainAction()
    data class RemoveTask(val task: Task) : MainAction()
    data class ChangeTaskCompletion (val task: Task) : MainAction()
    data class SelectTask(val task: Task) : MainAction()
}