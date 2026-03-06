package com.mkulikov.taskb.group_api.database

import kotlinx.coroutines.flow.Flow

interface IGroupRepository {

    val groupEntities: Flow<List<GroupEntity>>

    suspend fun addGroup(
        name: String,
        color: Int,
    )

    suspend fun groupExists(name: String): Boolean

    suspend fun getGroupById(groupId: Int): GroupEntity?

    suspend fun deleteById(groupId: Int)

    suspend fun updateGroup(groupEntity: GroupEntity)
}