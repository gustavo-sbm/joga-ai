package com.jogaai.ui.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jogaai.theme.Spacing

@Composable
fun EstadoDaTela(
    mensagem: String,
    modifier: Modifier = Modifier,
    textoBotao: String? = null,
    onBotaoClick: (() -> Unit)? = null
    ) {
    Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(Spacing.md)
        ) {
            Text(mensagem, color = MaterialTheme.colorScheme.onSurfaceVariant)
            if(textoBotao != null && onBotaoClick != null) {
                OutlinedButton(
                    onClick = onBotaoClick,
                ) {
                    Text(textoBotao)
                }

            }
        }
    }
}