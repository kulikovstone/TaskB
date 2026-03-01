package com.mkulikov.taskb.task.repository

import com.mkulikov.taskb.task_api.database.TaskEntity
import com.mkulikov.taskb.task_api.database.TaskDao
import com.mkulikov.taskb.group_api.database.GroupDao
import com.mkulikov.taskb.group_api.database.GroupEntity
import kotlinx.coroutines.flow.Flow

interface IMainRepository {

    val groups: Flow<List<GroupEntity>>

    val tasks: Flow<List<TaskEntity>>

    suspend fun addGroup(group: GroupEntity)

    suspend fun updateGroup(group: GroupEntity)

    suspend fun removeGroup(group: GroupEntity)

    suspend fun addTask(taskEntity: TaskEntity)

    suspend fun updateTask(taskEntity: TaskEntity)

    suspend fun removeTask(taskEntity: TaskEntity)
}


class MainRepository(
    private val groupDao: GroupDao,
    private val taskDao: TaskDao,
) : IMainRepository {

    override val groups: Flow<List<GroupEntity>> = groupDao.getGroups()

    override val tasks: Flow<List<TaskEntity>> = taskDao.getTasks()

    override suspend fun addGroup(group: GroupEntity) = groupDao.insert(group)

    override suspend fun updateGroup(group: GroupEntity) = groupDao.update(group)

    override suspend fun removeGroup(group: GroupEntity) = groupDao.delete(group)

    override suspend fun addTask(taskEntity: TaskEntity) = taskDao.insert(taskEntity)

    override suspend fun updateTask(taskEntity: TaskEntity) = taskDao.update(taskEntity)

    override suspend fun removeTask(taskEntity: TaskEntity) = taskDao.delete(taskEntity)
}