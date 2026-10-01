package com.jogaai.ui.emprestimo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SecondaryScrollableTabRow
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import  androidx.compose.runtime.getValue
import  androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.jogaai.data.AppModule
import com.jogaai.domain.model.StatusEmprestimo
import com.jogaai.ui.shared.EstadoDaTela

// TODO: Atualizar as cores corretamente e usar o when dos estados
@Composable
fun EmprestimoScreen(
    modifier: Modifier = Modifier,
    viewModel: EmprestimoViewModel = remember {
        EmprestimoViewModel(AppModule.emprestimoRepository, AppModule.userRepository, AppModule.jogoRepository)
    }
) {
    val uiState by viewModel.uiState.collectAsState()
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
            selectedTabIndex = uiState.categoriaSelecionada.ordinal,
            containerColor = Color.Transparent,
            edgePadding = 0.dp
        ) {
            StatusEmprestimo.entries.forEach { filter ->
                Tab(
                    selected = filter == uiState.categoriaSelecionada,
                    onClick = {
                        viewModel.atulizarCategoria(filter)
                    },
                    text = { Text(filter.name, color = MaterialTheme.colorScheme.background) }
                )
            }
        }
        when {
            uiState.isLoading -> {
                Box(modifier = Modifier.fillMaxWidth().weight(1f), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            uiState.errorMessage.isNotEmpty() -> EstadoDaTela(
                mensagem = uiState.errorMessage,
                modifier = Modifier.weight(1f),
                textoBotao = "Tentar novamente",
                onBotaoClick = viewModel::carregar
            )

            uiState.emprestimosFiltrados.isEmpty() -> EstadoDaTela(
                mensagem = "Nenhum empréstimo",
                modifier = Modifier.weight(1f),
            )
            else -> {
                val tabela = gerarTabelaEmprestimos(uiState.emprestimosFiltrados, uiState.usuariosEmprestimos, uiState.jogosExemplares)
                TabelaComponente(tabela)
            }
        }
    }

}