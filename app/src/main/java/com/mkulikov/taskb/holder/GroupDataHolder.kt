package com.mkulikov.taskb.holder

import com.mkulikov.taskb.data.IMainRepository
import com.mkulikov.taskb.database.Group
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

interface IGroupDataHolder {

    val groups: Flow<List<Group>>

    val selected: MutableStateFlow<Group?>

    fun selectGroup(group: Group)

    fun unselectGroup()
}

class GroupDataHolder(
    private val repository: IMainRepository,
): IGroupDataHolder {

    override val groups = repository.groups

    override val selected = MutableStateFlow<Group?>(null)

    override fun selectGroup(group: Group) = selected.update { group }

    override fun unselectGroup() = selected.update { null }
}