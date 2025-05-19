package com.mkulikov.taskb.ui.screen.edit_group

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mkulikov.taskb.GROUP_NAME_MAX_LENGTH
import com.mkulikov.taskb.ui.screen.add_group.ColorPalette
import com.mkulikov.taskb.ui.screen.loading.LoadingScreen

@Composable
fun EditGroupScreen(
    state: EditGroupState,
    action: (EditGroupAction) -> Unit,
) {
    when (state) {
        EditGroupState.Loading -> LoadingScreen()
        is EditGroupState.Data -> Content(
            oldName = state.oldName,
            selectedColor = state.selectedColor,
            action = action,
        )

        is EditGroupState.Error -> {
            // todo Придумать как отображать ошибку
        }
    }
}

@Composable
private fun Content(
    oldName: String,
    selectedColor: Color,
    action: (EditGroupAction) -> Unit,
) {
    val focusRequester = remember { FocusRequester() }
    var name by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.TopCenter,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Изменение группы",
                fontSize = 20.sp,
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                modifier = Modifier
                    .focusRequester(focusRequester)
                    .height(56.dp), // Иначе прыгает высота при вводе (не из-за trailingIcon)
                value = name,
                onValueChange = {
                    if (it.length <= GROUP_NAME_MAX_LENGTH) name = it
                    else it.take(GROUP_NAME_MAX_LENGTH)
                },
                singleLine = true,
                placeholder = {
                    Text("Введите имя группы...")
                },
                trailingIcon = {
                    if (name.isNotEmpty()) {
                        IconButton(
                            modifier = Modifier.size(30.dp),
                            onClick = {
                                name = ""
                                focusRequester.requestFocus()
                            },
                        ) {
                            Icon(
                                modifier = Modifier.size(16.dp),
                                imageVector = Icons.Outlined.Clear,
                                contentDescription = null,
                            )
                        }
                    }
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = { action(EditGroupAction.EditGroup(name, selectedColor)) }
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            ColorPalette(
                selectedColor = selectedColor,
                select = { color -> action(EditGroupAction.ChangeColor(color)) }
            )

            Spacer(modifier = Modifier.height(32.dp))

            Row {
                TextButton(
                    onClick = { action(EditGroupAction.RemoveGroup) },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text("Удалить")
                }

                Spacer(modifier = Modifier.width(16.dp))

                TextButton(
                    onClick = { action(EditGroupAction.Back) },
                ) {
                    Text("Отмена")
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    enabled = name.isNotBlank(),
                    onClick = { action(EditGroupAction.EditGroup(name, selectedColor)) },
                ) {
                    Text("Сохранить")
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        name = oldName
        focusRequester.requestFocus()
    }
}