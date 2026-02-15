package com.mkulikov.taskb.task.screen_old.error

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ErrorScreen(
    goBack: () -> Unit,
    text: String? = null,
    @DrawableRes image: Int? = null,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column {
            Text("Что-то пошло не так...")
            Button(
                onClick = {},
            ) {
                Text("Назад")
            }
        }
    }
}