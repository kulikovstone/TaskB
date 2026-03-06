package com.mkulikov.taskb.group.screen.edit_group

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkulikov.taskb.core.navigation.IAppNavigator
import com.mkulikov.taskb.designsystem.color.GroupColor
import com.mkulikov.taskb.designsystem.dialog.Confirmation
import com.mkulikov.taskb.designsystem.dialog.Message
import com.mkulikov.taskb.group.interactor.IGroupInteractor
import com.mkulikov.taskb.group.screen.Group
import com.mkulikov.taskb.group.screen.GroupDialog
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class EditGroupViewModel(
    private val groupId: Int,
    private val appNavigator: IAppNavigator,
    private val groupInteractor: IGroupInteractor,
) : ViewModel() {

    private val _state = MutableStateFlow(EditGroupState())
    val state = _state.asStateFlow()

    private var editingGroup: Group? = null

    init {
        viewModelScope.launch {
            groupInteractor.getGroupById(groupId)?.let { group ->
                editingGroup = group
            }

            // todo Получить статистику группы

            editingGroup?.let { group ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        name = group.name,
                        groupColor = group.color,
                        statistics = Statistics(),
                    )
                }
            }
        }
    }

    fun onEvent(event: EditGroupEvent) {
        when (event) {
            EditGroupEvent.Back -> appNavigator.back()
            is EditGroupEvent.NameChanged -> nameChanged(event.name)
            is EditGroupEvent.ColorChanged -> colorChanged(event.groupColor)
            EditGroupEvent.UpdateGroup -> updateGroup()
            EditGroupEvent.PrepareDeleteGroup -> prepareDeleteGroup()
            EditGroupEvent.DeleteGroup -> deleteGroup()
            EditGroupEvent.PrepareDeleteGroupWithTasks -> prepareDeleteGroupWithTasks()
            EditGroupEvent.DeleteGroupWithTasks -> deleteGroupWithTasks()
            EditGroupEvent.ClearDialogs -> clearDialogs()
        }
    }

    private fun nameChanged(name: String) {
        _state.update {
            it.copy(
                name = name,
                isDataValid = name.trim().isNotEmpty(),
            )
        }

        updateDataChangeState()
    }

    private fun colorChanged(groupColor: GroupColor) {
        _state.update {
            it.copy(
                groupColor = groupColor,
            )
        }

        updateDataChangeState()
    }

    private fun updateDataChangeState() {
        val isDataChanged = isNameChanged() || isColorChanged()
        _state.update { it.copy(isDataChanged = isDataChanged) }
    }

    private fun updateGroup() = viewModelScope.launch {
        if (isNameChanged()) {
            val groupExists = groupInteractor.groupExists(state.value.name.trim())
            if (groupExists) {
                _state.update {
                    it.copy(
                        nameConflictMessage = GroupDialog.nameConflictMessage,
                    )
                }
                return@launch
            }
        }

        _state.update { it.copy(isLoading = true) }

        groupInteractor.updateGroup(
            Group(
                id = groupId,
                name = state.value.name.trim(),
                color = state.value.groupColor,
            )
        )

        appNavigator.back()
    }

    private fun prepareDeleteGroup() {
        _state.update {
            it.copy(
                deleteGroupConfirmation = GroupDialog.deleteGroupConfirmation,
            )
        }
    }

    private fun deleteGroup() = viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }

        // todo Удаляем группу из всех связанных задач

        groupInteractor.deleteById(groupId)
        appNavigator.back()
    }

    private fun prepareDeleteGroupWithTasks() {
        _state.update {
            it.copy(
                deleteGroupWithTasksConfirmation = GroupDialog.deleteGroupWithTasksConfirmation,
            )
        }
    }

    private fun deleteGroupWithTasks() = viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }

        // todo Удаляем все связанные задачи

        groupInteractor.deleteById(groupId)
        appNavigator.back()
    }

    private fun clearDialogs() {
        _state.update {
            it.copy(
                nameConflictMessage = null,
                deleteGroupConfirmation = null,
                deleteGroupWithTasksConfirmation = null,
            )
        }
    }

    private fun isNameChanged(): Boolean {
        return editingGroup?.name != state.value.name.trim()
    }

    private fun isColorChanged(): Boolean {
        return editingGroup?.color?.name != state.value.groupColor.name
    }
}

@Immutable
internal data class EditGroupState(
    val isLoading: Boolean = true,
    val isDataChanged: Boolean = false,
    val isDataValid: Boolean = true,
    val name: String = "",
    val groupColor: GroupColor = GroupColor.random(),
    val statistics: Statistics = Statistics(),
    val nameConflictMessage: Message? = null,
    val deleteGroupConfirmation: Confirmation? = null,
    val deleteGroupWithTasksConfirmation: Confirmation? = null,
)

@Immutable
internal data class Statistics(
    val active: Int = 0,
    val completed: Int = 0,
    val deleted: Int = 0,
) {

    fun isEmpty(): Boolean {
        return active == 0 && completed == 0 && deleted == 0
    }
}

internal sealed interface EditGroupEvent {

    data object Back : EditGroupEvent

    data class NameChanged(val name: String) : EditGroupEvent

    data class ColorChanged(val groupColor: GroupColor) : EditGroupEvent

    data object UpdateGroup : EditGroupEvent

    data object PrepareDeleteGroup : EditGroupEvent

    data object DeleteGroup : EditGroupEvent

    data object PrepareDeleteGroupWithTasks : EditGroupEvent

    data object DeleteGroupWithTasks : EditGroupEvent

    data object ClearDialogs : EditGroupEvent
}