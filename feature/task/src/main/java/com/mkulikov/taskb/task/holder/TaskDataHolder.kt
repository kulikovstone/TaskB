package com.mkulikov.taskb.task.holder

import com.mkulikov.taskb.task_api.database.TaskEntity
import com.mkulikov.taskb.task.repository.IMainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

interface ITaskDataHolder {

    val tasks: Flow<List<TaskEntity>>

    val selected: MutableStateFlow<TaskEntity?>

    fun changeSelection(taskEntity: TaskEntity)

    fun selectTask(taskEntity: TaskEntity)

    fun unselectTask()
}

class TaskDataHolder(
    private val repository: IMainRepository,
) : ITaskDataHolder {

    override val tasks = repository.tasks

    override val selected = MutableStateFlow<TaskEntity?>(null)

    override fun changeSelection(taskEntity: TaskEntity) {
        if (selected.value == taskEntity) {
            unselectTask()
        } else {
            selectTask(taskEntity)
        }
    }

    override fun selectTask(taskEntity: TaskEntity) = selected.update { taskEntity }

    override fun unselectTask() = selected.update { null }
}