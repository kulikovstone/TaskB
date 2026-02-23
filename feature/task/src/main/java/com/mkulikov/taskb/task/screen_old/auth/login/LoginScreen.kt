package com.mkulikov.taskb.task.screen_old.auth.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mkulikov.taskb.designsystem.component.EmailField
import com.mkulikov.taskb.designsystem.component.PasswordField
import com.mkulikov.taskb.designsystem.screen.loading.LoadingScreen

@Composable
fun LoginScreen(
    state: LoginState,
    intent: (LoginIntent) -> Unit,
) {

    when (state) {
        LoginState.Loading -> LoadingScreen()
        LoginState.Data -> Content(
            intent = intent,
        )

        LoginState.Error -> {}
    }
}

@Composable
private fun Content(
    intent: (LoginIntent) -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 30.dp)
        ) {
            EmailField(
                modifier = Modifier
                    .widthIn(max = 500.dp)
                    .fillMaxWidth(),
                value = email,
                onChange = { data -> email = data }
            )

            Spacer(modifier = Modifier.height(8.dp))

            PasswordField(
                modifier = Modifier
                    .widthIn(max = 500.dp)
                    .fillMaxWidth(),
                value = password,
                onChange = { data -> password = data },
                submit = { intent(LoginIntent.Login(email, password)) }
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { intent(LoginIntent.Login(email, password)) },
                enabled = email.isNotEmpty() && password.isNotEmpty()
            ) {
                Text(text = "Войти")
            }

            Spacer(modifier = Modifier.height(8.dp))

            TextButton(
                onClick = { intent(LoginIntent.Registration) }
            ) {
                Text(text = "Регистрация")
            }
        }
    }
}