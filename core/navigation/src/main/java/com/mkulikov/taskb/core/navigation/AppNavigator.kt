package com.mkulikov.taskb.core.navigation

import androidx.compose.runtime.Stable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.NavKey

interface IAppNavigator {
    fun push(key: NavKey)
    fun pop()
}

@Stable
internal class AppNavigator(
    private val backStack: SnapshotStateList<NavKey>
) : IAppNavigator {

    override fun push(key: NavKey) {
        backStack.add(key)
    }

    override fun pop() {
        if (backStack.size <= 1) return
        backStack.removeLastOrNull()
    }
}