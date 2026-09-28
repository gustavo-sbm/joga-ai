package com.jogaai.ui.jogo

import com.jogaai.domain.model.Categoria
import com.jogaai.domain.model.Exemplar
import com.jogaai.domain.model.Jogo


data class JogoScreenUiState (
    val isLoading: Boolean = false,
    val categoriaSelecionada: Categoria? = null ,
    val errorMessage: String = "",
    val busca: String = "",
    val jogos: List<Jogo> = emptyList(),
    val categorias: List<Categoria> = emptyList(),
    val exemplaresPorJogo: Map<String, List<Exemplar>> = emptyMap(),
) {

    val temFiltroAtivo: Boolean
        get() = busca.isNotBlank() || categoriaSelecionada != null

    val jogosFiltrados: List<Jogo>
        get() = jogos
            .filter { it.ativo }
            .filter { categoriaSelecionada == null || categoriaSelecionada in it.categorias }
            .filter { busca.isBlank() || it.nome.contains(busca, ignoreCase = true) }
}