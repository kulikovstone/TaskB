package com.mkulikov.taskb.group.repository

import com.mkulikov.taskb.group_api.database.GroupDao
import com.mkulikov.taskb.group_api.database.GroupEntity
import com.mkulikov.taskb.group_api.database.IGroupRepository
import kotlinx.coroutines.flow.Flow

internal class GroupRepository(
    private val groupDao: GroupDao,
) : IGroupRepository {

    override val groupEntities: Flow<List<GroupEntity>> = groupDao.getGroups()

    override suspend fun addGroup(name: String, color: Int) {
        groupDao.insert(
            groupEntity = GroupEntity(
                name = name,
                color = color,
            )
        )
    }

    override suspend fun groupExists(name: String): Boolean {
        return groupDao.groupExists(name)
    }

    override suspend fun getGroupById(groupId: Int): GroupEntity? {
        return groupDao.getGroupById(groupId)
    }

    override suspend fun deleteById(groupId: Int) {
        groupDao.deleteById(groupId)
    }

    override suspend fun updateGroup(groupEntity: GroupEntity) {
        groupDao.update(groupEntity)
    }
}