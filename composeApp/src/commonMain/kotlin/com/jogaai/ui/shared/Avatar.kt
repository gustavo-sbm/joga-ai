package com.jogaai.ui.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private fun iniciais(nome: String) =
    nome.trim()
        .split(" ")
        .filter { it.length > 2 }
        .take(2)
        .map { it.first().uppercaseChar() }
        .joinToString("")
        .ifEmpty { "?" }


@Composable
fun Avatar(nome: String, modifier: Modifier = Modifier, tamanho: Dp = 36.dp,
           corFundo: Color = MaterialTheme.colorScheme.secondaryContainer,
           corTexto: Color = MaterialTheme.colorScheme.onSecondaryContainer){
    Box(
        modifier = modifier.size(tamanho)
            .clip(RoundedCornerShape(30))
            .background(corFundo),
        contentAlignment = Alignment.Center
    ) {
        Text(text = iniciais(nome), style = MaterialTheme.typography.labelMedium, maxLines = 1,
            color = corTexto)
    }
}