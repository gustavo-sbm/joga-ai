package com.jogaai.desktop

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.jogaai.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "JogaAí",
    ) {
        App()
    }
}



