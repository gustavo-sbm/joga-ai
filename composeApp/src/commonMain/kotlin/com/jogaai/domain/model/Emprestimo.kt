package com.jogaai.domain.model

import kotlinx.datetime.LocalDate

data class Emprestimo(
    val id: String,
    val usuarioId: String,
    val dataEmprestimo: LocalDate,
    val dataPrevistaDevolucao: LocalDate,
    val status: StatusEmprestimo,
    val exemplares: List<Exemplar>,
    val dataCadastro: LocalDate,
    val dataDevolucao: LocalDate? = null,
    val observacoes: String? = null,
    val dataAlteracao: LocalDate? = null
)
