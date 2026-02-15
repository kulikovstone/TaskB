package com.mkulikov.taskb.home.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.mkulikov.taskb.navigation.IAppNavigator
import com.mkulikov.taskb.navigation.INavEntry
import com.mkulikov.taskb.home.screen.start.HomeScreen
import com.mkulikov.taskb.home.screen.start.HomeViewModel
import com.mkulikov.taskb.home_api.navigation.Home
import org.koin.androidx.compose.koinViewModel

class HomeNavEntry() : INavEntry {

    override fun register(scope: EntryProviderScope<NavKey>, navigator: IAppNavigator) =
        with(scope) {
            entry<Home> {
                val viewModel = koinViewModel<HomeViewModel>()

                HomeScreen(
                    intent = viewModel::handleIntent,
                )
            }
        }
}