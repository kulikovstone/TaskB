package com.mkulikov.taskb.group.screen

import com.mkulikov.taskb.designsystem.dialog.Confirmation
import com.mkulikov.taskb.designsystem.dialog.Message

object GroupDialog {

    val nameConflictMessage = Message(
        title = "Name Conflict",
        text = "Group with this name already exists.",
    )

    val deleteGroupConfirmation = Confirmation(
        title = "Delete group",
        text = "The group will be deleted.",
        confirmText = "Delete",
    )

    val deleteGroupWithTasksConfirmation = Confirmation(
        title = "Delete group",
        text = "The group and all associated tasks will be deleted.",
        confirmText = "Delete All",
    )
}