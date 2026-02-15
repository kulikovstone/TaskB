package com.mkulikov.taskb.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mkulikov.taskb.core.designsystem.theme.TaskBTheme
import com.mkulikov.taskb.core.navigation.AppRoot

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskBTheme {
                AppRoot()
            }
        }
    }
}