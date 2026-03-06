package com.mkulikov.taskb.group.screen.edit_group

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.mkulikov.taskb.designsystem.block.ActionBlock
import com.mkulikov.taskb.designsystem.button.ActionButton
import com.mkulikov.taskb.designsystem.color.GroupColor
import com.mkulikov.taskb.designsystem.dialog.ConfirmationDialog
import com.mkulikov.taskb.designsystem.dialog.MessageDialog
import com.mkulikov.taskb.designsystem.screen.loading.LoadingScreen
import com.mkulikov.taskb.designsystem.theme.TaskBTheme
import com.mkulikov.taskb.group.screen.edit_group.part.GroupStatisticsBlock
import com.mkulikov.taskb.group.screen.part.GroupColorBlock

@Composable
internal fun EditGroupScreen(
    state: EditGroupState,
    event: (EditGroupEvent) -> Unit
) {
    when {
        state.isLoading -> LoadingScreen()
        else -> EditGroupContent(
            state = state,
            event = event,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EditGroupContent(
    state: EditGroupState,
    event: (EditGroupEvent) -> Unit,
) {
    Scaffold(
        contentWindowInsets = WindowInsets.statusBars,
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { event(EditGroupEvent.Back) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "ArrowBack",
                        )
                    }
                },
                title = {
                    Text("Group")
                },
                actions = {
                    IconButton(
                        onClick = { event(EditGroupEvent.UpdateGroup) },
                        enabled = state.isDataValid && state.isDataChanged,
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = "Done",
                        )
                    }
                },
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = state.name,
                onValueChange = { event(EditGroupEvent.NameChanged(it)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                placeholder = {
                    Text("Enter group name")
                },
                trailingIcon = {
                    if (state.name.isNotEmpty()) {
                        IconButton(
                            onClick = { event(EditGroupEvent.NameChanged("")) },
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Clear,
                                contentDescription = "Clear"
                            )
                        }
                    }
                }
            )

            GroupColorBlock(
                selectedColor = state.groupColor,
                onClick = { event(EditGroupEvent.ColorChanged(it)) },
            )

            GroupStatisticsBlock(state.statistics)

            ActionBlock {
                ActionButton(
                    label = "Delete",
                    imageVector = Icons.Outlined.Delete,
                    onClick = { event(EditGroupEvent.PrepareDeleteGroup) }
                )

                if (!state.statistics.isEmpty()) {
                    ActionButton(
                        label = "Delete with tasks",
                        imageVector = Icons.Outlined.Delete,
                        onClick = { event(EditGroupEvent.PrepareDeleteGroupWithTasks) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(50.dp))
        }

        state.nameConflictMessage?.let { message ->
            MessageDialog(
                message = message,
                onDismiss = { event(EditGroupEvent.ClearDialogs) }
            )
        }

        state.deleteGroupConfirmation?.let { confirmation ->
            ConfirmationDialog(
                confirmation = confirmation,
                onConfirm = { event(EditGroupEvent.DeleteGroup) },
                onDismiss = { event(EditGroupEvent.ClearDialogs) }
            )
        }

        state.deleteGroupWithTasksConfirmation?.let { confirmation ->
            ConfirmationDialog(
                confirmation = confirmation,
                onConfirm = { event(EditGroupEvent.DeleteGroupWithTasks) },
                onDismiss = { event(EditGroupEvent.ClearDialogs) }
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun EditGroupScreenPreview() = TaskBTheme {
    EditGroupScreen(
        state = EditGroupState(
            isLoading = false,
            name = "Home work",
            groupColor = GroupColor.LightGreen,
            statistics = Statistics(
                active = 1,
                completed = 3,
                deleted = 7,
            ),
        ),
        event = {},
    )
}