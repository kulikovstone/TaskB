package com.mkulikov.taskb.group_api.database

import kotlinx.coroutines.flow.Flow

interface IGroupRepository {

    val groups: Flow<List<GroupEntity>>

    suspend fun addGroup(
        name: String,
        color: Int,
    )

    suspend fun groupExists(name: String): Boolean
}