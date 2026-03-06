package com.mkulikov.taskb.group.holder

import com.mkulikov.taskb.group_api.database.GroupEntity
import com.mkulikov.taskb.group_api.database.IGroupRepository
import com.mkulikov.taskb.group_api.holder.IGroupEntityHolder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class GroupEntityHolder(
    private val groupRepository: IGroupRepository,
): IGroupEntityHolder {

    override val groups = groupRepository.groupEntities

    override val selected = MutableStateFlow<GroupEntity?>(null)

    override fun selectGroup(group: GroupEntity) = selected.update { group }

    override fun unselectGroup() = selected.update { null }
}