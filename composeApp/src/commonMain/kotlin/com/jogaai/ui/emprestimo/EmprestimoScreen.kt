package com.jogaai.ui.emprestimo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SecondaryScrollableTabRow
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import  androidx.compose.runtime.getValue
import  androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

enum class Teste(val index: Int, val value: String) {
    TESTE1(0, "Em curso 3"),
    TESTE2(1, "Atrasados 2"),
    TESTE3(2, "Histórico 4")
}

val tabelaTeste = Tabela<EmprestimosTeste>(
    colunas = colunas,
    linhas = linhas
)

// TODO: Atualizar as cores corretamente e usar o when dos estados
@Composable
fun EmprestimoScreen(
    modifier: Modifier = Modifier
) {
    var selected by remember { mutableStateOf(Teste.TESTE1) }
    Column(
        modifier = modifier.padding(15.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Empréstimos",
                    fontSize = 30.sp
                )
                Text("Um empréstimo pode levar até 3 exemplares e vence em 7 dias.")
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = {}) {
                Text("Registrar empréstimo")
            }
        }
        SecondaryScrollableTabRow(
            modifier = modifier,
            selectedTabIndex = Teste.entries.indexOf(selected),
            containerColor = Color.Transparent,
            edgePadding = 0.dp
        ) {
            Teste.entries.forEach { filter ->
                Tab(
                    selected = filter == selected,
                    onClick = {
                        selected = filter
                    },
                    text = { Text(filter.value, color = MaterialTheme.colorScheme.background) }
                )
            }
        }
        when(selected) {
            Teste.TESTE1 -> TabelaComponente(tabelaTeste)
            Teste.TESTE2 -> Text("Atrasados 2")
            Teste.TESTE3 -> Text("Histórico 4")
        }
    }

}