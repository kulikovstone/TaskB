package com.mkulikov.taskb.group.screen.group_list

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkulikov.taskb.core.navigation.IAppNavigator
import com.mkulikov.taskb.group.interactor.IGroupInteractor
import com.mkulikov.taskb.group.navigation.GroupInternalRoute
import com.mkulikov.taskb.group.screen.Group
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

internal class GroupListViewModel(
    private val appNavigator: IAppNavigator,
    private val groupInteractor: IGroupInteractor,
) : ViewModel() {

    private val _state = MutableStateFlow(GroupListState())
    val state = _state.asStateFlow()

    init {
        observeGroups()
    }

    private fun observeGroups() {
        groupInteractor.groups
            .onEach { groups ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        groups = groups.toImmutableList(),
                    )
                }
            }
            .catch { _ ->
                appNavigator.back()
            }
            .launchIn(viewModelScope)
    }

    fun onEvent(event: GroupListEvent) {
        when (event) {
            GroupListEvent.Back -> appNavigator.back()
            GroupListEvent.AddGroup -> appNavigator.navigate(GroupInternalRoute.AddGroup)
            is GroupListEvent.EditGroup -> {
                appNavigator.navigate(GroupInternalRoute.EditGroup(event.groupId))
            }
        }
    }
}

@Immutable
internal data class GroupListState(
    val isLoading: Boolean = true,
    val groups: ImmutableList<Group> = persistentListOf(),
)

sealed class GroupListEvent {

    data object Back : GroupListEvent()
    data object AddGroup : GroupListEvent()
    data class EditGroup(val groupId: Int) : GroupListEvent()
}