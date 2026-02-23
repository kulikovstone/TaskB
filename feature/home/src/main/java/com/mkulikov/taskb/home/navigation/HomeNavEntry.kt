package com.mkulikov.taskb.home.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.mkulikov.taskb.core.navigation.IAppNavigator
import com.mkulikov.taskb.core.navigation.INavEntry
import com.mkulikov.taskb.home.screen.start.HomeScreen
import com.mkulikov.taskb.home.screen.start.HomeViewModel
import com.mkulikov.taskb.home_api.navigation.HomeRoute
import org.koin.androidx.compose.koinViewModel

class HomeNavEntry() : INavEntry {

    override fun register(scope: EntryProviderScope<NavKey>, navigator: IAppNavigator) =
        with(scope) {
            entry<HomeRoute.Home> {
                val viewModel = koinViewModel<HomeViewModel>()

                HomeScreen(
                    intent = viewModel::handleIntent,
                )
            }
        }
}