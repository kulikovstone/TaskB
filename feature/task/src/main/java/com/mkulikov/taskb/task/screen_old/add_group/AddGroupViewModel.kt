package com.mkulikov.taskb.task.screen_old.add_group

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkulikov.taskb.feature.task_data.database.Group
import com.mkulikov.taskb.task.repository.IMainRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddGroupViewModel(
    private val repository: IMainRepository,
) : ViewModel() {

    val state = MutableStateFlow<AddGroupState>(
        AddGroupState.Data(selectedColor = Color.Transparent)
    )

    var toBack: (() -> Unit)? = null

    fun handleIntent(intent: AddGroupIntent) {
        viewModelScope.launch {
            when (intent) {
                AddGroupIntent.Back -> toBack?.invoke()
                is AddGroupIntent.ChangeColor -> changeColor(intent.color)
                is AddGroupIntent.AddGroup -> addGroup(intent.name, intent.color)
            }
        }
    }

    private fun changeColor(color: Color) {
        state.update { AddGroupState.Data(selectedColor = color) }
    }

    private suspend fun addGroup(name: String, color: Color) {
        state.update { AddGroupState.Loading }
        repository.addGroup(
            Group(
                name = name,
                color = color,
            )
        )
        toBack?.invoke()
    }
}

sealed class AddGroupState {

    data object Loading : AddGroupState()
    data class Data(val selectedColor: Color) : AddGroupState()
    data class Error(val message: String) : AddGroupState()
}

sealed class AddGroupIntent {

    data object Back : AddGroupIntent()
    data class ChangeColor(val color: Color) : AddGroupIntent()
    data class AddGroup(val name: String, val color: Color) : AddGroupIntent()
}