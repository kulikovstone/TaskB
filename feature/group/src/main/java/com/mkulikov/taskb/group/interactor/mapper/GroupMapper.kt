package com.mkulikov.taskb.group.interactor.mapper

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.mkulikov.taskb.designsystem.color.GroupColor
import com.mkulikov.taskb.group.screen.Group
import com.mkulikov.taskb.group_api.database.GroupEntity

internal class GroupMapper {

    fun mapToGroup(groupEntity: GroupEntity) = Group(
        id = groupEntity.id,
        name = groupEntity.name,
        color = GroupColor.fromColor(Color(groupEntity.color)),
    )

    fun mapToGroupEntity(group: Group) = GroupEntity (
        id = group.id,
        name = group.name,
        color = group.color.color.toArgb(),
    )
}