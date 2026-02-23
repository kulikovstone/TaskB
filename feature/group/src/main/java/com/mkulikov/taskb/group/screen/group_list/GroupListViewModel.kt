package com.mkulikov.taskb.group.screen.group_list

import android.util.Log
import androidx.lifecycle.ViewModel
import com.mkulikov.taskb.core.navigation.IAppNavigator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class GroupListViewModel(
    val appNavigator: IAppNavigator,
) : ViewModel() {

    val state = MutableStateFlow<GroupListState>(GroupListState.Loading)

    init {
        state.update { getGroupLisMock() }
    }

    fun handleIntent(intent: GroupListIntent) {
        when (intent) {
            GroupListIntent.Back -> appNavigator.pop()
            GroupListIntent.AddGroup -> Log.w("!!!", "AddGroup")
            is GroupListIntent.OpenGroup -> Log.w("!!!", "OpenGroup")
        }
    }
}


sealed class GroupListState {

    data object Loading : GroupListState()
    data class Data(
        val title: String,
        val groups: List<Group>,
    ) : GroupListState()
}

sealed class GroupListIntent {

    data object Back : GroupListIntent()
    data object AddGroup : GroupListIntent()
    data class OpenGroup(val group: Group) : GroupListIntent()
}