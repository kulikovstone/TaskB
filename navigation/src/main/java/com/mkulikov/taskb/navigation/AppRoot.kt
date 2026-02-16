package com.mkulikov.taskb.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.mkulikov.taskb.home_api.navigation.Home
import org.koin.compose.getKoin

@Composable
fun AppRoot() {
    val koin = getKoin()

    val backStack = remember {
        koin.get<SnapshotStateList<NavKey>>().also { stack ->
            if (stack.isEmpty()) stack += Home
        }
    }
    val navigator = remember { koin.get<IAppNavigator>() }
    val registrars = remember { koin.getAll<INavEntry>() }

    NavDisplay(
        backStack = backStack,
        onBack = { navigator.pop() },
        entryProvider = entryProvider {
            registrars.forEach { it.register(this, navigator) }
        }
    )
}