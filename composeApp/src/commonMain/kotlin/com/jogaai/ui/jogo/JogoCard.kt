package com.jogaai.ui.jogo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jogaai.domain.model.Exemplar
import com.jogaai.domain.model.Jogo
import com.jogaai.domain.model.StatusExemplar
import com.jogaai.theme.Spacing
import com.jogaai.theme.VerdeDisponivel
import com.jogaai.theme.corDaTampa
import com.jogaai.theme.corDoStatus

private fun textoJogadores(jogo: Jogo): String =
    if(jogo.maxJogadores == jogo.minJogadores) "${jogo.maxJogadores} jogadores" else "${jogo.minJogadores}-${jogo.maxJogadores} jogadores"

private fun textoDuracao(jogo: Jogo): String =
    if(jogo.minDuracao == jogo.maxDuracao) "${jogo.maxDuracao} min" else "${jogo.minDuracao}-${jogo.maxDuracao} min"

@Composable
fun JogoCard(jogo: Jogo, exemplares: List<Exemplar>, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface   // ← esta
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(104.dp)
                .background(corDaTampa(jogo.nome)),
            contentAlignment = Alignment.BottomStart
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(Spacing.sm)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White.copy(alpha = 0.9f))
                    .padding(horizontal = Spacing.sm, vertical = Spacing.xs)
            ) {
                Text(textoJogadores(jogo), style = MaterialTheme.typography.labelSmall)
            }
            Text(
                text = jogo.nome,
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White,
                modifier = Modifier.padding(Spacing.md))
        }

        Column(
            modifier = Modifier.padding(Spacing.md),
            verticalArrangement = Arrangement.spacedBy(Spacing.xs) ){
            Text(
                text = "${textoDuracao(jogo)} · ${jogo.idadeMinima}+ · ${jogo.editora ?: "—"}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            val livres = exemplares.count { it.status == StatusExemplar.DISPONIVEL }
            val (texto, cor) = if (livres > 0) {
                "$livres de ${exemplares.size} ${if (livres > 1) "livres" else "livre"}" to VerdeDisponivel
            } else {
                "Todos emprestados" to MaterialTheme.colorScheme.onSurfaceVariant
            }

            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {

                Text(texto, color = cor, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.SemiBold)

                Text("${exemplares.size} "+ if(exemplares.size>1) "exemplares" else "exemplar",
                    style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }

            Row(horizontalArrangement = Arrangement.spacedBy(Spacing.xs)) {
                exemplares.take(8).forEach { exemplar ->
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(corDoStatus(exemplar.status))
                    )
                }
            }

        }
    }
}