package com.mkulikov.taskb.group_api.holder

import com.mkulikov.taskb.group_api.database.GroupEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

interface IGroupEntityHolder {

    val groups: Flow<List<GroupEntity>>

    val selected: MutableStateFlow<GroupEntity?>

    fun selectGroup(group: GroupEntity)

    fun unselectGroup()
}