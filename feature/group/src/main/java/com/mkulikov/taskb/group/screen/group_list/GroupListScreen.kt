package com.mkulikov.taskb.group.screen.group_list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.mkulikov.taskb.designsystem.screen.loading.LoadingScreen
import com.mkulikov.taskb.designsystem.theme.TaskBTheme
import com.mkulikov.taskb.group.screen.group_list.part.GroupItem

@Composable
internal fun GroupListScreen(
    state: GroupListState,
    event: (GroupListEvent) -> Unit,
) {
    when {
        state.isLoading -> LoadingScreen()
        else -> GroupListContent(
            state = state,
            event = event,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GroupListContent(
    state: GroupListState,
    event: (GroupListEvent) -> Unit,
) {
    Scaffold(
        contentWindowInsets = WindowInsets.statusBars,
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { event(GroupListEvent.Back) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "ArrowBack",
                        )
                    }
                },
                title = {
                    Text(
                        text = "Groups",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
                actions = {
                    IconButton(onClick = {  }) {
                        Icon(
                            imageVector = Icons.Filled.FilterAlt,
                            contentDescription = "Filters",
                        )
                    }
                    IconButton(onClick = { event(GroupListEvent.AddGroup) }) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Add",
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            contentPadding = PaddingValues(bottom = 50.dp),
        ) {
            items(
                items = state.groups,
                key = { it.id },
            ) { group ->
                GroupItem(
                    name = group.name,
                    groupColor = group.color,
                    openGroup = { event(GroupListEvent.EditGroup(group.id)) },
                )
            }
        }
    }
}

@Composable
@PreviewLightDark
private fun GroupListScreenPreview() = TaskBTheme {
//    GroupListScreen (
//        state = getGroupLisMock(),
//        intent = {},
//    )
}