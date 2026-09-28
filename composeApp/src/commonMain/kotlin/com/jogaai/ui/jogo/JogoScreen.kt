package com.jogaai.ui.jogo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jogaai.theme.Spacing
import com.jogaai.ui.shared.EstadoDaTela

@Composable
fun JogoScreen(viewModel: JogoViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    Column(Modifier.fillMaxSize()){
        OutlinedTextField(
            value = uiState.busca,
            onValueChange = viewModel::atualizarBusca,
            placeholder = { Text("Buscar jogo") },
            leadingIcon = {Icon(Icons.Default.Search, contentDescription = null) },
            singleLine = true,
            modifier = Modifier.widthIn(max = 420.dp)
                .padding(horizontal = Spacing.lg, vertical = Spacing.md),
            shape = RoundedCornerShape(50),
            textStyle = MaterialTheme.typography.bodyMedium,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            )
        )
        CategoriaFilterRow(
            categorias = uiState.categorias,
            selecionada = uiState.categoriaSelecionada,
            onSelecionar = viewModel::selecionarCategoria,
            modifier = Modifier.padding(bottom = Spacing.md)
        )
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
            uiState.jogosFiltrados.isEmpty() -> EstadoDaTela(
                mensagem = if(uiState.temFiltroAtivo) "Nenhum jogo encontrado" else "Nenhum jogo cadastrado",
                textoBotao = if(uiState.temFiltroAtivo) "Limpar filtros" else null,
                modifier = Modifier.weight(1f),
                onBotaoClick = viewModel::limparFiltros
            )
            else ->
                LazyVerticalGrid(
                    modifier = Modifier.weight(1f),
                    columns = GridCells.Adaptive(minSize = 230.dp),
                    contentPadding = PaddingValues(Spacing.lg),
                    verticalArrangement = Arrangement.spacedBy(Spacing.md),
                    horizontalArrangement = Arrangement.spacedBy(Spacing.md)
                ) {
                    items(uiState.jogosFiltrados) {jogo ->
                        JogoCard(jogo = jogo, exemplares = uiState.exemplaresPorJogo[jogo.id] ?: emptyList())
                    }
                }
        }


    }

}