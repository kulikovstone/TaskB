package com.mkulikov.taskb.holder

import com.mkulikov.taskb.data.IMainRepository
import com.mkulikov.taskb.database.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

interface ITaskDataHolder {

    val tasks: Flow<List<Task>>

    val selected: MutableStateFlow<Task?>

    fun changeSelection(task: Task)

    fun selectTask(task: Task)

    fun unselectTask()
}

class TaskDataHolder(
    private val repository: IMainRepository,
) : ITaskDataHolder {

    override val tasks = repository.tasks

    override val selected = MutableStateFlow<Task?>(null)

    override fun changeSelection(task: Task) {
        if (selected.value == task) {
            unselectTask()
        } else {
            selectTask(task)
        }
    }

    override fun selectTask(task: Task) = selected.update { task }

    override fun unselectTask() = selected.update { null }
}