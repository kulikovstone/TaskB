package com.mkulikov.taskb.group.screen.add_group

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkulikov.taskb.core.navigation.IAppNavigator
import com.mkulikov.taskb.designsystem.color.GroupColor
import com.mkulikov.taskb.designsystem.dialog.Message
import com.mkulikov.taskb.group.dialog.GroupDialog
import com.mkulikov.taskb.group_api.database.IGroupRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class AddGroupViewModel(
    private val appNavigator: IAppNavigator,
    private val groupRepository: IGroupRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(AddGroupState())
    val state = _state.asStateFlow()

    fun onEvent(event: AddGroupEvent) {
        when (event) {
            AddGroupEvent.Back -> appNavigator.back()
            is AddGroupEvent.ColorChanged -> colorChanged(event.groupColor)
            is AddGroupEvent.NameChanged -> nameChanged(event.name)
            AddGroupEvent.SaveGroup -> saveGroup()
            AddGroupEvent.ClearDialogs -> clearDialogs()
        }
    }

    private fun nameChanged(name: String) {
        _state.update {
            it.copy(
                name = name,
                normalizedName = name.trim()
            )
        }
    }

    private fun colorChanged(color: GroupColor) {
        _state.update { it.copy(groupColor = color) }
    }

    private fun saveGroup() = viewModelScope.launch {
        val groupExists = groupRepository.groupExists(state.value.normalizedName)
        if (groupExists) {
            _state.update {
                it.copy(nameConflictMessage = GroupDialog.nameConflictMessage)
            }
            return@launch
        }

        _state.update { it.copy(isLoading = true) }

        groupRepository.addGroup(
            name = state.value.normalizedName,
            color = state.value.groupColor.color.toArgb(),
        )

        appNavigator.back()
    }

    private fun clearDialogs() {
        _state.update {
            it.copy(
                nameConflictMessage = null,
            )
        }
    }
}

@Immutable
internal data class AddGroupState(
    val isLoading: Boolean = false,
    val name: String = "",
    val normalizedName: String = "",
    val groupColor: GroupColor = GroupColor.random(),
    val nameConflictMessage: Message? = null,
)

internal sealed interface AddGroupEvent {

    data object Back : AddGroupEvent

    data class NameChanged(val name: String) : AddGroupEvent

    data class ColorChanged(val groupColor: GroupColor) : AddGroupEvent

    data object SaveGroup : AddGroupEvent

    data object ClearDialogs : AddGroupEvent
}