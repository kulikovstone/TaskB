package com.mkulikov.taskb.group.database

import com.mkulikov.taskb.group_api.database.GroupDao
import com.mkulikov.taskb.group_api.database.GroupEntity
import com.mkulikov.taskb.group_api.database.IGroupRepository
import kotlinx.coroutines.flow.Flow

internal class GroupRepository(
    private val groupDao: GroupDao,
) : IGroupRepository {

    override val groups: Flow<List<GroupEntity>> = groupDao.getGroups()

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
}