package com.jogaai.ui.emprestimo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun <T> TabelaComponente(
    tabela: Tabela<T>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.background(Color.LightGray)
    ) {
        TabelaCabecalho(colunas = tabela.colunas, modifier = Modifier.background(color = MaterialTheme.colorScheme.onBackground))
        tabela.linhas.forEach { linha ->
            HorizontalDivider()
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                tabela.colunas.forEach { coluna ->
                    Box(
                        modifier = Modifier.weight(coluna.peso)
                    ) {
                        coluna.conteudo(linha)
                    }
                }
            }
        }

    }
}

@Composable
private fun <T> TabelaCabecalho(
    modifier: Modifier = Modifier,
    colunas: List<TabelaColuna<T>>
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        colunas.forEach { colunas ->
            Text(
                text = colunas.nome,
                modifier = Modifier.weight(colunas.peso)
            )
        }
    }
}