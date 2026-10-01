package com.jogaai.ui.emprestimo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.unit.sp
import com.jogaai.domain.model.Emprestimo
import com.jogaai.domain.model.Jogo
import com.jogaai.domain.model.User
import com.jogaai.theme.Spacing
import com.jogaai.theme.corDoStatus
import com.jogaai.ui.shared.BadgeStatus
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.todayIn
import kotlin.time.Clock

val hoje = Clock.System.todayIn(TimeZone.currentSystemDefault())

val colunas = listOf<TabelaColuna<EmprestimoTabela>>(
    TabelaColuna("Código", 1.0f) {
        valor -> Text(
            "#${valor.emprestimo.id}",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodySmall
        )
    },

    TabelaColuna("Pessoa", 1.5f) {
        valor -> Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier.padding(vertical = Spacing.md)
                    .size(34.dp)
                    .clip(RoundedCornerShape(20))
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Text(iniciais(valor.usuario.nome), color = MaterialTheme.colorScheme.onPrimary)
            }
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(valor.usuario.nome)
                Text(valor.usuario.status.toString())
            }
        }
    },

    TabelaColuna("Exemplares", 1.0f) {
        valor -> Column {
            valor.jogos.forEach { jogo ->
                Column {
                    Text(jogo.nome)
                    Text("#${jogo.id}")
                }
            }
        }
    },

    TabelaColuna("Retirada", 1.0f) {
        valor -> Text("${valor.emprestimo.dataCadastro.day}/${valor.emprestimo.dataCadastro.month.ordinal + 1}/${valor.emprestimo.dataCadastro.year}")
    },

    TabelaColuna("Prazo", 1.0f) { valor ->
        val diasRestantes = hoje.daysUntil(valor.emprestimo.dataPrevistaDevolucao)
        Column {
            Text("${valor.emprestimo.dataPrevistaDevolucao.day}/${valor.emprestimo.dataPrevistaDevolucao.month.ordinal + 1}/${valor.emprestimo.dataPrevistaDevolucao.year}")
            Text("Faltam $diasRestantes dias")
        }
    },

    TabelaColuna("Situação", 0.5f) {
        valor ->
        BadgeStatus(valor.emprestimo.status.description, corDoStatus(valor.emprestimo.status))
    },

    TabelaColuna("", 1.0f) {
        valor -> Button(onClick = {}) {
            Text("Registrar devolução")
        }
    }
)

// TODO: Colocar num arquivo certo dps
fun gerarTabelaEmprestimos(
    emprestimos: List<Emprestimo>,
    usuarios: Map<String, User>,
    jogos: Map<String, Jogo>
): Tabela<EmprestimoTabela> {
    return Tabela(
        colunas = colunas,
        linhas = emprestimos.map { emprestimo ->
            EmprestimoTabela(
                emprestimo = emprestimo,
                jogos = emprestimo.exemplares.map { exemplar ->
                    requireNotNull(jogos[exemplar.jogoId]) {
                        "Jogo ${exemplar.jogoId} não encontrado"
                    }
                },
                usuario = requireNotNull(usuarios[emprestimo.usuarioId]) {
                    "Usuário ${emprestimo.usuarioId} não encontrado"
                }
            )
        }
    )
}


data class EmprestimoTabela(
    val emprestimo: Emprestimo,
    val jogos: List<Jogo>,
    val usuario: User
)

fun iniciais(nome: String): String {
    return Regex("""\b([A-Za-zÀ-ÿ])""")
        .findAll(nome)
        .take(2)
        .joinToString("") { it.groupValues[1].uppercase() }
}
