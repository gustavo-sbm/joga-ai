package com.jogaai.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jogaai.data.AppModule
import com.jogaai.ui.login.LoginScreen
import com.jogaai.ui.login.LoginViewModel
import com.jogaai.ui.register.RegisterScreen
import com.jogaai.ui.register.RegisterViewModel

@Composable
fun AuthNavigation (onLoginSuccess: () -> Unit) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Login,
        enterTransition = { valEnterTransition },
        exitTransition = { navExitTransition },
        popEnterTransition = { valEnterTransition },
        popExitTransition = { navExitTransition }
    ) {

        composable<Login> {
            val viewModel = remember { LoginViewModel(AppModule.authRepository) }
            LoginScreen(
                viewModel = viewModel,
                onNavigateToRegister = { navController.navigate(Register)},
                onLoginSuccess = onLoginSuccess
            )
        }

        composable<Register> {
            val viewModel = remember { RegisterViewModel(AppModule.authRepository) }
            RegisterScreen(
                viewModel = viewModel,
                onRegisterSuccess = { navController.popBackStack() },
                onNavigateToLogin = { navController.popBackStack() }
            )
        }
    }
}
