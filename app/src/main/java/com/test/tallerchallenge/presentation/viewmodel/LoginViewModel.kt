package com.test.tallerchallenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.tallerchallenge.common.Response
import com.test.tallerchallenge.domain.LoginRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {
    private var _uiState = MutableStateFlow<LoginUIState>(LoginUIState.Idle)
    val uiState = _uiState.asStateFlow()

    private var _navigation = Channel<LoginNavigation>(Channel.BUFFERED)
    val navigation = _navigation.receiveAsFlow()

    fun login(username: String, password: String) = viewModelScope.launch {
        _uiState.value = LoginUIState.Loading
        when (val response = loginRepository.login(username, password)) {
            is Response.Success -> {
                _uiState.value = LoginUIState.Success("Login successful")
                delay(1000)
                _navigation.send(LoginNavigation.ToHome)
            }

            is Response.Error -> {
                _uiState.value = LoginUIState.Error(response.message)
            }
        }
    }
}


sealed class LoginUIState {
    data object Idle : LoginUIState()
    data object Loading : LoginUIState()
    data class Error(val message: String) : LoginUIState()
    data class Success(val message: String) : LoginUIState()
}

sealed class LoginNavigation {
    data object ToHome : LoginNavigation()
}