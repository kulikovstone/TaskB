package com.mkulikov.taskb.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mkulikov.taskb.ui.screen.add_group.AddGroupScreen
import com.mkulikov.taskb.ui.screen.add_group.AddGroupViewModel
import com.mkulikov.taskb.ui.screen.edit_group.EditGroupScreen
import com.mkulikov.taskb.ui.screen.edit_group.EditGroupViewModel
import com.mkulikov.taskb.ui.screen.main.MainScreen
import com.mkulikov.taskb.ui.screen.main.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun TaskBNavGraph(
    navController: NavHostController,
) = NavHost(
    navController = navController,
    startDestination = Screen.Main.name,
) {
    composable(Screen.Main.name) {
        val viewModel = koinViewModel<MainViewModel>()
        viewModel.toAddGroup = { navController.navigate(Screen.AddGroup.name) }
        viewModel.toEditGroup = { navController.navigate(Screen.EditGroup.name) }
        val state by viewModel.state.collectAsStateWithLifecycle()

        MainScreen(
            state = state,
            action = viewModel::action,
        )
    }

    composable(Screen.AddGroup.name) {
        val viewModel = koinViewModel<AddGroupViewModel>()
        viewModel.toBack = { navController.popBackStack() }
        val state by viewModel.state.collectAsStateWithLifecycle()

        AddGroupScreen(
            state = state,
            action = viewModel::action,
        )
    }

    composable(Screen.EditGroup.name) {
        val viewModel = koinViewModel<EditGroupViewModel>()
        viewModel.toBack = { navController.popBackStack() }
        val state by viewModel.state.collectAsStateWithLifecycle()

        EditGroupScreen(
            state = state,
            action = viewModel::action,
        )
    }
}