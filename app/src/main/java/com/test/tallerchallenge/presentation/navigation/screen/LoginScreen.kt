package com.test.tallerchallenge.presentation.navigation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.test.tallerchallenge.presentation.viewmodel.LoginNavigation
import com.test.tallerchallenge.presentation.viewmodel.LoginUIState
import com.test.tallerchallenge.presentation.viewmodel.LoginViewModel

@Composable
fun LoginScreen(viewModel: LoginViewModel, navigateToHome: () -> Unit) {
    val uiState by viewModel.uiState.collectAsState()
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(viewModel.navigation) {
        viewModel.navigation.collect { navigation ->
            when (navigation) {
                LoginNavigation.ToHome -> navigateToHome()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") }
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") }
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Button(onClick = { viewModel.login(username, password) }) {
            Text("Login")
        }
    }

    when (val state = uiState) {
        is LoginUIState.Error -> Text(state.message, color = Color.Red)
        LoginUIState.Idle -> {}
        LoginUIState.Loading -> CircularProgressIndicator(modifier = Modifier.background(Color.White))
        is LoginUIState.Success -> Text(state.message, color = Color.Green)
    }
}