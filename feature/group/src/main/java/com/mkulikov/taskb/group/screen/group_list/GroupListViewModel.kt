package com.mkulikov.taskb.group.screen.group_list

import androidx.lifecycle.ViewModel
import com.mkulikov.taskb.core.navigation.IAppNavigator
import com.mkulikov.taskb.group.navigation.GroupInternalRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GroupListViewModel(
    val appNavigator: IAppNavigator,
) : ViewModel() {

    private val _state = MutableStateFlow<GroupListState>(GroupListState.Loading)
    val state = _state.asStateFlow()

    init {
        _state.update { getGroupLisMock() }
    }

    fun onEvent(event: GroupListEvent) {
        when (event) {
            GroupListEvent.Back -> appNavigator.pop()
            GroupListEvent.AddGroup -> appNavigator.push(GroupInternalRoute.AddGroup)
            is GroupListEvent.EditGroup -> appNavigator.push(
                GroupInternalRoute.EditGroup(event.groupId)
            )
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

sealed class GroupListEvent {

    data object Back : GroupListEvent()
    data object AddGroup : GroupListEvent()
    data class EditGroup(val groupId: Int) : GroupListEvent()
}