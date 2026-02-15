package com.mkulikov.taskb.task.repository

import com.mkulikov.taskb.task.data.Group
import com.mkulikov.taskb.task.data.GroupDao
import com.mkulikov.taskb.task.data.Task
import com.mkulikov.taskb.task.data.TaskDao
import kotlinx.coroutines.flow.Flow

interface IMainRepository {

    val groups: Flow<List<Group>>

    val tasks: Flow<List<Task>>

    suspend fun addGroup(group: Group)

    suspend fun updateGroup(group: Group)

    suspend fun removeGroup(group: Group)

    suspend fun addTask(task: Task)

    suspend fun updateTask(task: Task)

    suspend fun removeTask(task: Task)
}


class MainRepository(
    private val groupDao: GroupDao,
    private val taskDao: TaskDao,
) : IMainRepository {

    override val groups: Flow<List<Group>> = groupDao.getGroups()

    override val tasks: Flow<List<Task>> = taskDao.getTasks()

    override suspend fun addGroup(group: Group) = groupDao.insert(group)

    override suspend fun updateGroup(group: Group) = groupDao.update(group)

    override suspend fun removeGroup(group: Group) = groupDao.delete(group)

    override suspend fun addTask(task: Task) = taskDao.insert(task)

    override suspend fun updateTask(task: Task) = taskDao.update(task)

    override suspend fun removeTask(task: Task) = taskDao.delete(task)
}