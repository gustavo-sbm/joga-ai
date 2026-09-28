package com.jogaai.ui.jogo

import com.jogaai.data.exception.AppException
import com.jogaai.data.repository.ExemplarRepository
import com.jogaai.data.repository.JogoRepository
import com.jogaai.domain.model.Categoria
import com.jogaai.ui.shared.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class JogoViewModel(
    private val repository: JogoRepository,
    private val exemplarRepository: ExemplarRepository
) : BaseViewModel<JogoScreenUiState>(JogoScreenUiState()) {

    fun atualizarBusca(texto: String) {
        _uiState.value = _uiState.value.copy(busca = texto)

    }

    fun selecionarCategoria(categoria: Categoria?) {
        val nova = if (_uiState.value.categoriaSelecionada == categoria) null else categoria
        _uiState.value = _uiState.value.copy(categoriaSelecionada = nova)
    }

    fun limparFiltros() {
        _uiState.value = _uiState.value.copy(busca = "", categoriaSelecionada = null)
    }

    fun carregar() {
        runAction("Erro ao carregar") {
            val lista = repository.listar()
            val exemplar = exemplarRepository.listar()
            _uiState.value = _uiState.value.copy(
                jogos = lista,
                categorias = lista.flatMap { it.categorias }.distinct().sortedBy { it.nome },
                exemplaresPorJogo = exemplar.groupBy { it.jogoId }
            )
        }
    }

    override fun comCarregando(
        estado: JogoScreenUiState,
        carregando: Boolean
    ): JogoScreenUiState {
         return estado.copy(isLoading = carregando)
    }

    override fun comErro(
        estado: JogoScreenUiState,
        mensagem: String
    ): JogoScreenUiState {
        return estado.copy(errorMessage = mensagem)
    }

    init {
        carregar()
    }
}
