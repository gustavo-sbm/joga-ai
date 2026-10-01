package com.jogaai.ui.emprestimo

import com.jogaai.domain.model.Emprestimo
import com.jogaai.domain.model.Jogo
import com.jogaai.domain.model.StatusEmprestimo
import com.jogaai.domain.model.User

data class EmprestimoScreenUiState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val emprestimos: List<Emprestimo> = emptyList(),
    val usuariosEmprestimos: Map<String, User> = emptyMap(),
    val jogosExemplares: Map<String, Jogo> = emptyMap(),
    val categoriaSelecionada: StatusEmprestimo = StatusEmprestimo.ATIVO
) {
    val emprestimosFiltrados: List<Emprestimo>
        get() = emprestimos.filter { it.status == categoriaSelecionada }
}