package com.jogaai.ui.emprestimo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jogaai.theme.Spacing
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.todayIn
import kotlin.time.Clock

val hoje = Clock.System.todayIn(TimeZone.currentSystemDefault())

data class PessoaTeste(val acronimo: String, val nome: String, val status: String)
data class ExemplarTeste(val nome: String, val id: Int)

data class EmprestimosTeste(val codigo: String, val pessoa: PessoaTeste, val exemplares: List<ExemplarTeste>, val retirada: LocalDate, val prazo: LocalDate, val situação: String)

val colunas = listOf<TabelaColuna<EmprestimosTeste>>(
    TabelaColuna("Código", 1.0f) {
        valor -> Text(
            "#${valor.codigo}",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodySmall
        )
    },

    TabelaColuna("Pessoa", 2.0f) {
        valor -> Row {
            Box(
                modifier = Modifier.padding(vertical = Spacing.md)
                    .size(34.dp)
                    .clip(RoundedCornerShape(20))
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Text(valor.pessoa.acronimo, color = MaterialTheme.colorScheme.onPrimary)
            }
            Column {
                Text(valor.pessoa.nome)
                Text(valor.pessoa.status)
            }
        }
    },

    TabelaColuna("Exemplares", 1.0f) {
        valor -> Column {
            valor.exemplares.forEach { exemplar ->
                Column {
                    Text(exemplar.nome)
                    Text("#${exemplar.id}")
                }
            }
        }
    },

    TabelaColuna("Retirada", 1.0f) {
        valor -> Text("${valor.retirada.day}/${valor.retirada.month.ordinal + 1}/${valor.retirada.year}")
    },

    TabelaColuna("Prazo", 1.0f) { valor ->
        val diasRestantes = hoje.daysUntil(valor.prazo)
        Column {
            Text("${valor.prazo.day}/${valor.prazo.month.ordinal + 1}/${valor.prazo.year}")
            Text("Faltam $diasRestantes dias")
        }
    },

    TabelaColuna("Situação", 1.0f) {
        valor -> Text(valor.situação)
    }
)

val linhas = listOf<EmprestimosTeste>(
    EmprestimosTeste(
        codigo = "EMP001",
        pessoa = PessoaTeste("JD", "João da Silva", "Ativo"),
        exemplares = listOf(ExemplarTeste("Livro A", 1), ExemplarTeste("Livro B", 2)),
        retirada = LocalDate(2023, 1, 1),
        prazo = LocalDate(2023, 1, 8),
        situação = "Em aberto"
    ),
    EmprestimosTeste(
        codigo = "EMP002",
        pessoa = PessoaTeste("MC", "Maria da Costa", "Ativo"),
        exemplares = listOf(ExemplarTeste("Livro C", 3), ExemplarTeste("Livro D", 4)),
        retirada = LocalDate(2023, 1, 1),
        prazo = LocalDate(2023, 1, 8),
        situação = "Em aberto"
    ),
    EmprestimosTeste(
        codigo = "EMP003",
        pessoa = PessoaTeste("PS", "Pedro da Silva", "Ativo"),
        exemplares = listOf(ExemplarTeste("Livro E", 5), ExemplarTeste("Livro F", 6)),
        retirada = LocalDate(2023, 1, 1),
        prazo = LocalDate(2023, 1, 8),
        situação = "Em aberto"
    )
)