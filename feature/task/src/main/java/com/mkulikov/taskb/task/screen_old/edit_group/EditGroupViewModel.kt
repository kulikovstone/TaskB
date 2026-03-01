package com.mkulikov.taskb.task.screen_old.edit_group

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkulikov.taskb.task.repository.IMainRepository
import com.mkulikov.taskb.task.holder.IGroupDataHolder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EditGroupViewModel(
    private val repository: IMainRepository,
    private val groupHolder: IGroupDataHolder,
) : ViewModel() {

    private val group = groupHolder.selected

    val state = MutableStateFlow<EditGroupState>(
        EditGroupState.Data(
            oldName = group.value?.name.orEmpty(),
            selectedColor = Color.Transparent,
        )
    )

    var toBack: (() -> Unit)? = null

    // todo Добавить логику отображения диалога
    // если в группе есть задачи
    // отображение сообщения об удалении этих задач
    // Кнопки - удалить (удаляет все), отмена

    fun handleIntent(intent: EditGroupIntent) {
        viewModelScope.launch {
            when (intent) {
                EditGroupIntent.Back -> toBack?.invoke()
                is EditGroupIntent.ChangeColor -> changeColor(intent.color)
                EditGroupIntent.RemoveGroup -> removeGroup()
                is EditGroupIntent.EditGroup -> editGroup(intent.name, intent.color)
            }
        }
    }

    private fun changeColor(color: Color) {
        state.update {
            EditGroupState.Data(
                oldName = group.value?.name.orEmpty(),
                selectedColor = color,
            )
        }
    }

    private suspend fun removeGroup() {
        state.update { EditGroupState.Loading }
        group.value?.let { group ->
            repository.removeGroup(group)
        }
        toBack?.invoke()
    }

    private suspend fun editGroup(name: String, color: Color) {
        state.update { EditGroupState.Loading }
        group.value?.let { group ->
            if (group.name != name) {
                repository.updateGroup(
                    group.copy(name = name, color = color.toArgb())
                )
            }
        }
        toBack?.invoke()
    }
}

sealed class EditGroupState {

    data object Loading : EditGroupState()
    data class Data(
        val oldName: String,
        val selectedColor: Color,
    ) : EditGroupState()

    data class Error(val message: String) : EditGroupState()
}

sealed class EditGroupIntent {

    data object Back : EditGroupIntent()
    data class ChangeColor(val color: Color) : EditGroupIntent()
    data object RemoveGroup : EditGroupIntent()
    data class EditGroup(val name: String, val color: Color) : EditGroupIntent()
}