package com.mkulikov.taskb.task.holder

import com.mkulikov.taskb.group_api.database.GroupEntity
import com.mkulikov.taskb.task.repository.IMainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

interface IGroupDataHolder {

    val groups: Flow<List<GroupEntity>>

    val selected: MutableStateFlow<GroupEntity?>

    fun selectGroup(group: GroupEntity)

    fun unselectGroup()
}

class GroupDataHolder(
    private val repository: IMainRepository,
): IGroupDataHolder {

    override val groups = repository.groups

    override val selected = MutableStateFlow<GroupEntity?>(null)

    override fun selectGroup(group: GroupEntity) = selected.update { group }

    override fun unselectGroup() = selected.update { null }
}