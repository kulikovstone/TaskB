package com.mkulikov.taskb.group.interactor.mapper

import androidx.compose.ui.graphics.Color
import com.mkulikov.taskb.designsystem.color.GroupColor
import com.mkulikov.taskb.group.screen.Group
import com.mkulikov.taskb.group_api.database.GroupEntity

internal class GroupEntityMapper {

    fun mapToGroup(groupEntity: GroupEntity) = Group(
        id = groupEntity.id,
        name = groupEntity.name,
        color = GroupColor.fromColor(Color(groupEntity.color)),
    )
}