package com.mkulikov.taskb.group.interactor

import androidx.compose.ui.graphics.toArgb
import com.mkulikov.taskb.designsystem.color.GroupColor
import com.mkulikov.taskb.group.interactor.mapper.GroupEntityMapper
import com.mkulikov.taskb.group.screen.Group
import com.mkulikov.taskb.group_api.database.IGroupRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface IGroupInteractor {

    val groups: Flow<List<Group>>

    suspend fun groupExists(name: String): Boolean

    suspend fun addGroup(
        name: String,
        color: GroupColor,
    )

    suspend fun getGroupById(groupId: Int): Group?

    suspend fun deleteById(groupId: Int)
}

internal class GroupInteractor(
    private val groupRepository: IGroupRepository,
    private val groupEntityMapper: GroupEntityMapper,
) : IGroupInteractor {

    override val groups: Flow<List<Group>> = groupRepository.groupEntities.map { groupEntities ->
        groupEntities.map { groupEntity ->
            groupEntityMapper.mapToGroup(groupEntity)
        }
    }

    override suspend fun groupExists(name: String): Boolean {
        return groupRepository.groupExists(name)
    }

    override suspend fun addGroup(
        name: String,
        color: GroupColor
    ) {
        groupRepository.addGroup(
            name = name,
            color = color.color.toArgb(),
        )
    }

    override suspend fun getGroupById(groupId: Int): Group? {
        return groupRepository.getGroupById(groupId)?.let { groupEntity ->
            groupEntityMapper.mapToGroup(groupEntity)
        }
    }

    override suspend fun deleteById(groupId: Int) {
        groupRepository.deleteById(groupId)
    }
}