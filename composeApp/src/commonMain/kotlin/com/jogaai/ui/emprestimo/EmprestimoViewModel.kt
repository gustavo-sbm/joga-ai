package com.jogaai.ui.emprestimo

import com.jogaai.data.repository.EmprestimoRepository
import com.jogaai.data.repository.JogoRepository
import com.jogaai.data.repository.UserRepository
import com.jogaai.domain.model.StatusEmprestimo
import com.jogaai.ui.shared.BaseViewModel

class EmprestimoViewModel(
    private val repository: EmprestimoRepository,
    private val usuarioRepository: UserRepository,
    private val jogoRepository: JogoRepository
): BaseViewModel<EmprestimoScreenUiState>(EmprestimoScreenUiState()) {
    override fun comCarregando(
        estado: EmprestimoScreenUiState,
        carregando: Boolean
    ): EmprestimoScreenUiState {
        return estado.copy(isLoading = carregando)
    }

    override fun comErro(
        estado: EmprestimoScreenUiState,
        mensagem: String
    ): EmprestimoScreenUiState {
        return estado.copy(errorMessage = mensagem)
    }

    fun carregar() {
        runAction("Erro ao carregar empréstimos") {
            val emprestimos = repository.listar()
            _uiState.value = _uiState.value.copy(
                emprestimos = emprestimos,
                jogosExemplares = emprestimos.flatMap { it.exemplares }.map { it.jogoId }.distinct().associateWith { jogoRepository.buscarPorId(it) },
                usuariosEmprestimos = emprestimos.map{it.usuarioId}.distinct().associateWith { usuarioRepository.buscarPorId(it) }
            )

        }
    }

    fun atulizarCategoria(categoria: StatusEmprestimo) {
        _uiState.value = _uiState.value.copy(categoriaSelecionada = categoria)
    }

    init {
        carregar()
    }

}