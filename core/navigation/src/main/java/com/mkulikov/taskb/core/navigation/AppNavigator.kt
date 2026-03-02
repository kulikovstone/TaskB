package com.mkulikov.taskb.core.navigation

import androidx.compose.runtime.Stable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.NavKey

interface IAppNavigator {
    fun navigate(key: NavKey)
    fun back()
}

@Stable
internal class AppNavigator(
    private val backStack: SnapshotStateList<NavKey>
) : IAppNavigator {

    override fun navigate(key: NavKey) {
        backStack.add(key)
    }

    override fun back() {
        if (backStack.size <= 1) return
        backStack.removeLastOrNull()
    }
}