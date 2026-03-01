package com.mkulikov.taskb.home.screen.start

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.mkulikov.taskb.designsystem.theme.TaskBTheme

@Composable
fun HomeScreen(
    intent: (StartIntent) -> Unit,
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(onClick = { intent(StartIntent.OpenGroups) }) {
                Text(text = "Goals")
            }
            Spacer(
                modifier = Modifier.height(30.dp)
            )
            Button(onClick = { intent(StartIntent.OpenGroups) }) {
                Text(text = "Groups")
            }
            Spacer(
                modifier = Modifier.height(30.dp)
            )
            Button(onClick = { intent(StartIntent.OpenTasks) }) {
                Text(text = "Tasks")
            }
        }
    }
}

@PreviewLightDark
@Composable
fun HomeScreenPreview() {
    TaskBTheme {
        HomeScreen(
            intent = {},
        )
    }
}