package com.mkulikov.taskb.group.screen.add_group

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.mkulikov.taskb.designsystem.color.GroupColor
import com.mkulikov.taskb.designsystem.dialog.MessageDialog
import com.mkulikov.taskb.designsystem.screen.loading.LoadingScreen
import com.mkulikov.taskb.designsystem.theme.TaskBTheme
import com.mkulikov.taskb.group.screen.part.GroupColorBlock

@Composable
internal fun AddGroupScreen(
    state: AddGroupState,
    event: (AddGroupEvent) -> Unit
) {
    when {
        state.isLoading -> LoadingScreen()
        else -> AddGroupContent(
            state = state,
            event = event,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddGroupContent(
    state: AddGroupState,
    event: (AddGroupEvent) -> Unit,
) {
    val focusRequester = remember { FocusRequester() }

    Scaffold(
        contentWindowInsets = WindowInsets.statusBars,
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { event(AddGroupEvent.Back) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "ArrowBack",
                        )
                    }
                },
                title = {
                    Text(
                        text = "New group",
                    )
                },
                actions = {
                    IconButton(
                        onClick = { event(AddGroupEvent.SaveGroup) },
                        enabled = state.normalizedName.isNotEmpty(),
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
                onValueChange = { event(AddGroupEvent.NameChanged(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                singleLine = true,
                placeholder = {
                    Text("Enter group name")
                },
                trailingIcon = {
                    if (state.name.isNotEmpty()) {
                        IconButton(
                            onClick = { event(AddGroupEvent.NameChanged("")) },
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Clear,
                                contentDescription = "Clear"
                            )
                        }
                    }
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    capitalization = KeyboardCapitalization.Sentences,
                ),
                keyboardActions = KeyboardActions(
                    onDone = { event(AddGroupEvent.SaveGroup) }
                ),
            )

            GroupColorBlock(
                selectedColor = state.groupColor,
                onClick = { event(AddGroupEvent.ColorChanged(it)) },
            )

            Spacer(modifier = Modifier.height(50.dp))
        }

        state.nameConflictMessage?.let { message ->
            MessageDialog(
                message = message,
                onDismiss = { event(AddGroupEvent.ClearDialogs) },
            )
        }
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

@PreviewLightDark
@Composable
private fun AddGroupScreenPreview() = TaskBTheme {
    AddGroupScreen(
        state = AddGroupState(
            isLoading = false,
            groupColor = GroupColor.LightGreen,
        ),
        event = {},
    )
}