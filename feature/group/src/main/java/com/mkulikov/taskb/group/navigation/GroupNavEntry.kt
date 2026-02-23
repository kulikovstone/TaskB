package com.mkulikov.taskb.group.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.mkulikov.taskb.core.navigation.IAppNavigator
import com.mkulikov.taskb.core.navigation.INavEntry
import com.mkulikov.taskb.group.screen.group_list.GroupListScreen
import com.mkulikov.taskb.group.screen.group_list.GroupListViewModel
import com.mkulikov.taskb.group_api.navigation.GroupRoute
import org.koin.androidx.compose.koinViewModel

class GroupNavEntry() : INavEntry {

    override fun register(scope: EntryProviderScope<NavKey>, navigator: IAppNavigator) =
        with(scope) {
            entry<GroupRoute.GroupList> {
                val viewModel = koinViewModel<GroupListViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()

                GroupListScreen(
                    state = state,
                    intent = viewModel::handleIntent,
                )
            }
        }
}