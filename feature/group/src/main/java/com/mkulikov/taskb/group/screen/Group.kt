package com.mkulikov.taskb.group.screen

import androidx.compose.runtime.Immutable
import com.mkulikov.taskb.designsystem.color.GroupColor

@Immutable
data class Group(
    val id: Int,
    val name: String,
    val color: GroupColor,
)