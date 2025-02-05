package com.test.tallerchallenge.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.test.tallerchallenge.data.LoginRepositoryImpl
import com.test.tallerchallenge.presentation.navigation.screen.HomeScreen
import com.test.tallerchallenge.presentation.navigation.screen.LoginScreen
import com.test.tallerchallenge.presentation.viewmodel.LoginViewModel

@Composable
fun NavigationWrapper(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login",
        modifier = modifier
    ) {
        composable("login") {
            val repository = LoginRepositoryImpl()
            val viewModel = LoginViewModel(repository)
            val navigateToHome = { navController.navigate("home") }
            LoginScreen(viewModel, navigateToHome)
        }
        composable("home") {
            HomeScreen()
        }
    }
}