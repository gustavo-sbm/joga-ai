package com.jogaai.data.repository

import com.jogaai.domain.model.Reserva

interface ReservaRepository {
    suspend fun listar(): List<Reserva>
    suspend fun criar(reserva: Reserva)
    suspend fun deletar(id: String)
    suspend fun atualizar(reserva: Reserva)
    suspend fun buscarPorId(id: String): Reserva
}