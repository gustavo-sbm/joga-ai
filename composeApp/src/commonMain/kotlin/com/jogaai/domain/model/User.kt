package com.jogaai.domain.model

import kotlinx.datetime.LocalDate

data class User(
    val id: String,
    val nome: String,
    val email: String,
    val telefone: String,
    val status: StatusUser,
    val dataNascimento: LocalDate,
    val dataCadastro: LocalDate,
    val dataAlteracao: LocalDate? = null,
)