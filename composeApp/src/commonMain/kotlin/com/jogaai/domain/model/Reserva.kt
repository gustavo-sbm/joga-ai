package com.jogaai.domain.model

import kotlinx.datetime.LocalDate

data class Reserva(
    val id: String,
    val usuarioId: String,
    val jogoId: String,
    val dataReserva: LocalDate,
    val status: StatusReserva,
    val dataAtendimento: LocalDate? = null,
    val dataCancelamento: LocalDate? = null,
    val observacoes: String? = null,
)
