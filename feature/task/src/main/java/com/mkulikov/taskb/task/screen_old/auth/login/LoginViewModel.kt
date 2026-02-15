package com.mkulikov.taskb.task.screen_old.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkulikov.taskb.task.repository.IMainRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: IMainRepository,
) : ViewModel() {

    val state = MutableStateFlow<LoginState>(LoginState.Loading)

    var toTask: (() -> Unit)? = null
    var toRemember: (() -> Unit)? = null

    init {
        // Проверить есть ли у нас ключ
        // Если есть, то переход на экран задач

        // Если нет, то отображаем экран авторизации

        // todo Логику выбора стартового экрана перенести в MainActivity?

        viewModelScope.launch {
            delay(1000)
            state.update { LoginState.Data }
        }
    }

    fun handleIntent(intent: LoginIntent) {
        viewModelScope.launch {
            when (intent) {
                is LoginIntent.Login -> toTask?.invoke()
                is LoginIntent.Registration -> TODO()
                LoginIntent.Remember -> toRemember?.invoke()
            }
        }
    }

    private fun logIn(email: String, password: String) {
        // Лоадер
        // Валидация емайла и пароля
        // Если некорректные данные, то ошибка
        // Если ок, то запрос на сервер

        // Получаем токен
        // Сохраняем токен
        // Переходим на следующий экран

        // Иначе отображаем ошибку
    }

//    private fun isValidEmail(email: String): Boolean {
//        return true
//    }
//
//    private fun isValidPassword(password: String): Boolean {
//        return true
//    }
}

sealed class LoginState {

    data object Loading : LoginState()
    data object Data : LoginState()
    data object Error : LoginState()
}

sealed class LoginIntent {

    data class Login(val email: String, val password: String) : LoginIntent()
    data object Registration : LoginIntent()
    data object Remember : LoginIntent()
}