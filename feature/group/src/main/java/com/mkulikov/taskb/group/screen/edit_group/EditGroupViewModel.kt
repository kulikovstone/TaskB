package com.mkulikov.taskb.group.screen.edit_group

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkulikov.taskb.core.navigation.IAppNavigator
import com.mkulikov.taskb.designsystem.color.GroupColor
import com.mkulikov.taskb.designsystem.dialog.Confirmation
import com.mkulikov.taskb.designsystem.dialog.Message
import com.mkulikov.taskb.group.dialog.GroupDialog
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class EditGroupViewModel(
    private val appNavigator: IAppNavigator
) : ViewModel() {

    private var groupId: Int? = null

    private val _state = MutableStateFlow(EditGroupState())
    val state = _state.asStateFlow()

    init {
        // Получаем группу по groupId, если он не null
        // Сетим новое значение с данными группы или по умолчанию

        viewModelScope.launch {
            delay(300)

            // Или есть
            _state.update {
                it.copy(
                    isLoading = false,
                    name = "Home work",
                    groupColor = GroupColor.LightGreen,
                    statistics = Statistics(
                        active = 12,
                        completed = 5,
                        deleted = 0,
                    ),
                )
            }
        }
    }

    fun onEvent(event: EditGroupEvent) {
        when (event) {
            EditGroupEvent.Back -> appNavigator.pop()
            is EditGroupEvent.NameChanged -> nameChanged(event.name)
            is EditGroupEvent.ColorChanged -> colorChanged(event.groupColor)
            EditGroupEvent.SaveGroup -> saveGroup()
            EditGroupEvent.PrepareDeleteGroup -> prepareDeleteGroup()
            EditGroupEvent.DeleteGroup -> deleteGroup()
            EditGroupEvent.PrepareDeleteGroupWithTasks -> prepareDeleteGroupWithTasks()
            EditGroupEvent.DeleteGroupWithTasks -> deleteGroupWithTasks()
            EditGroupEvent.ClearDialogs -> clearDialogs()
        }
    }

    private fun nameChanged(name: String) {
        _state.update { it.copy(name = name) }
    }

    private fun colorChanged(color: GroupColor) {
        _state.update { it.copy(groupColor = color) }
    }

    private fun saveGroup() {
        // Проверяем, что нет такой задачи
        val isGroupAlreadyExist = true

        if (isGroupAlreadyExist) {
            _state.update {
                it.copy(
                    nameConflictMessage = GroupDialog.nameConflictMessage,
                )
            }
            return
        }

        // Перед сохранением проверяем, что данные изменились (для существующей задачи)

        // Сохраняем через репозиторий
        // данные берем из текущего стейта
    }

    private fun prepareDeleteGroup() {
        _state.update {
            it.copy(
                deleteGroupConfirmation = GroupDialog.deleteGroupConfirmation,
            )
        }
    }

    private fun deleteGroup() {
        // Удаляем группу из всех связанных задач
        // Удаляем группу

        // Возвращаемся назад
//        appNavigator.pop()
    }

    private fun prepareDeleteGroupWithTasks() {
        _state.update {
            it.copy(
                deleteGroupWithTasksConfirmation = GroupDialog.deleteGroupWithTasksConfirmation,
            )
        }
    }

    private fun deleteGroupWithTasks() {
        // Удаляем все связанные задачи
        // Удаляем группу

        // Возвращаемся назад
//        appNavigator.pop()
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
}

@Immutable
internal data class EditGroupState(
    val isLoading: Boolean = true,
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

    data object SaveGroup : EditGroupEvent

    data object PrepareDeleteGroup : EditGroupEvent

    data object DeleteGroup : EditGroupEvent

    data object PrepareDeleteGroupWithTasks : EditGroupEvent

    data object DeleteGroupWithTasks : EditGroupEvent

    data object ClearDialogs : EditGroupEvent
}