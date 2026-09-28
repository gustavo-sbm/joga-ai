package com.to_do.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.jogaai.App

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // A tela desenha por baixo das barras de status/navegação do sistema —
        // é o app (não o Android) quem decide o padding, o que é essencial
        // para telas mobile-first bem comportadas.
        enableEdgeToEdge()
        setContent {
            App()
        }
    }
}
