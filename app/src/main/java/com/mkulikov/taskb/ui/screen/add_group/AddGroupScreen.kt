package com.mkulikov.taskb.ui.screen.add_group

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mkulikov.taskb.GROUP_NAME_MAX_LENGTH
import com.mkulikov.taskb.theme.amber400
import com.mkulikov.taskb.theme.blue400
import com.mkulikov.taskb.theme.blueGrey400
import com.mkulikov.taskb.theme.brown400
import com.mkulikov.taskb.theme.cyan400
import com.mkulikov.taskb.theme.deepOrange400
import com.mkulikov.taskb.theme.deepPurple400
import com.mkulikov.taskb.theme.green400
import com.mkulikov.taskb.theme.grey400
import com.mkulikov.taskb.theme.grey500
import com.mkulikov.taskb.theme.grey700
import com.mkulikov.taskb.theme.indigo400
import com.mkulikov.taskb.theme.lightBlue400
import com.mkulikov.taskb.theme.lightGreen400
import com.mkulikov.taskb.theme.lime400
import com.mkulikov.taskb.theme.orange400
import com.mkulikov.taskb.theme.pink400
import com.mkulikov.taskb.theme.purple400
import com.mkulikov.taskb.theme.red400
import com.mkulikov.taskb.theme.teal400
import com.mkulikov.taskb.theme.yellow400
import com.mkulikov.taskb.ui.screen.loading.LoadingScreen

@Composable
fun AddGroupScreen(
    state: AddGroupState,
    action: (AddGroupIntent) -> Unit,
) {
    when (state) {
        AddGroupState.Loading -> LoadingScreen()
        is AddGroupState.Data -> Content(
            selectedColor = state.selectedColor,
            action = action,
        )

        is AddGroupState.Error -> {
            // todo Придумать как отображать ошибку
        }
    }
}

@Composable
private fun Content(
    selectedColor: Color,
    action: (AddGroupIntent) -> Unit,
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
                text = "Новая группа",
                fontSize = 20.sp,
            )

            Spacer(modifier = Modifier.height(16.dp))

//            NameField(
//                placeholder = "Введите имя группы...",
//                maxLength = GROUP_NAME_MAX_LENGTH,
//                focusRequester = focusRequester,
//                onDone = { name ->
//                    action(AddGroupAction.AddGroup(name, selectedColor))
//                }
//            )

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
                    onDone = { action(AddGroupIntent.AddGroup(name, selectedColor)) }
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            ColorPalette(
                selectedColor = selectedColor,
                select = { color -> action(AddGroupIntent.ChangeColor(color)) }
            )

            Spacer(modifier = Modifier.height(32.dp))

            Row {
                TextButton(
                    onClick = { action(AddGroupIntent.Back) },
                ) {
                    Text("Отмена")
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    enabled = name.isNotBlank(),
                    onClick = { action(AddGroupIntent.AddGroup(name, selectedColor)) },
                ) {
                    Text("Сохранить")
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

@Composable
fun ColorPalette(
    selectedColor: Color?,
    select: (Color) -> Unit,
) {
    Column {
        Text("Цвет")

        Spacer(modifier = Modifier.height(8.dp))

        Row {
            ColoredCircle(Color.Transparent, selectedColor) { select(it) }
            ColoredCircle(red400, selectedColor) { select(it) }
            ColoredCircle(pink400, selectedColor) { select(it) }
            ColoredCircle(purple400, selectedColor) { select(it) }
            ColoredCircle(deepPurple400, selectedColor) { select(it) }
            ColoredCircle(indigo400, selectedColor) { select(it) }
            ColoredCircle(blue400, selectedColor) { select(it) }
            ColoredCircle(lightBlue400, selectedColor) { select(it) }
            ColoredCircle(cyan400, selectedColor) { select(it) }
            ColoredCircle(teal400, selectedColor) { select(it) }

        }
        Row {
            ColoredCircle(green400, selectedColor) { select(it) }
            ColoredCircle(lightGreen400, selectedColor) { select(it) }
            ColoredCircle(lime400, selectedColor) { select(it) }
            ColoredCircle(yellow400, selectedColor) { select(it) }
            ColoredCircle(amber400, selectedColor) { select(it) }
            ColoredCircle(orange400, selectedColor) { select(it) }
            ColoredCircle(deepOrange400, selectedColor) { select(it) }
            ColoredCircle(brown400, selectedColor) { select(it) }
            ColoredCircle(grey400, selectedColor) { select(it) }
            ColoredCircle(blueGrey400, selectedColor) { select(it) }
        }
    }
}


@Composable
fun ColoredCircle(
    circleColor: Color,
    selectedColor: Color?,
    onClick: (Color) -> Unit,
) {
    val isNoneColor = selectedColor == null && circleColor == Color.Transparent
    val isSelected = circleColor == selectedColor

    Box(
        modifier = Modifier
            .size(30.dp)
            .padding(4.dp)
            .clip(CircleShape)
            .background(color = circleColor)
            .border(1.dp, grey500, CircleShape)
            .clickable { onClick(circleColor) },
        contentAlignment = Alignment.Center,
    ) {
        if (isNoneColor || isSelected) {
            Icon(
                modifier = Modifier.size(16.dp),
                imageVector = Icons.Outlined.Done,
                contentDescription = null,
                tint = grey700,
            )
        }
    }
}