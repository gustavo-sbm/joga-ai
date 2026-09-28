package com.jogaai.domain.model

data class Exemplar(
    val id: String,
    val codigo: String,
    val jogoId: String,
    val status: StatusExemplar,
    val conservacao: EstadoConservacao,
    val observacao: String? = null
)
