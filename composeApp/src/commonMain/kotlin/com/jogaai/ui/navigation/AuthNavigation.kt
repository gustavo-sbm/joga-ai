package com.jogaai.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jogaai.ui.login.LoginScreen
import com.jogaai.ui.register.RegisterScreen

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
            LoginScreen(
                onNavigateToRegister = { navController.navigate(Register)},
                onLoginSuccess = onLoginSuccess
            )
        }

        composable<Register> {
            RegisterScreen(
                onRegisterSuccess = { navController.popBackStack() },
                onNavigateToLogin = { navController.popBackStack() }
            )
        }
    }
}
