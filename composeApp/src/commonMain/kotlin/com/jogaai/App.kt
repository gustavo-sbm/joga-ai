package com.jogaai

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jogaai.theme.JogaAiTheme
import com.jogaai.ui.navigation.AuthNavigation
import com.jogaai.ui.navigation.MainNavigation



@Composable
fun App() {
    var isLoggedIn by remember { mutableStateOf<Boolean?>(true) }


    JogaAiTheme {
       when(isLoggedIn){
            null -> {
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center){
                    CircularProgressIndicator()
                }
            }
           true -> MainNavigation(onLogout = { isLoggedIn = false })
           false -> AuthNavigation(onLoginSuccess = { isLoggedIn = true })
       }
    }
}
