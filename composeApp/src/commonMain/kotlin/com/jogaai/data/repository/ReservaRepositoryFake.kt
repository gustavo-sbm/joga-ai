package com.jogaai.data.repository

import com.jogaai.data.exception.AppException
import com.jogaai.domain.model.Reserva
import com.jogaai.domain.model.StatusReserva
import kotlinx.coroutines.delay
import kotlinx.datetime.LocalDate


object ReservasFake {
    val todas = listOf(
        Reserva(
            id = "r1", usuarioId = "u2", jogoId = "9",
            dataReserva = LocalDate(2026, 9, 26), status = StatusReserva.ATIVA
        ),
        Reserva(
            id = "r2", usuarioId = "u3", jogoId = "3",
            dataReserva = LocalDate(2026, 9, 20), status = StatusReserva.ATENDIDA,
            dataAtendimento = LocalDate(2026, 9, 25)
        ),
        Reserva(
            id = "r3", usuarioId = "u4", jogoId = "5",
            dataReserva = LocalDate(2026, 8, 28), status = StatusReserva.CANCELADA,
            dataCancelamento = LocalDate(2026, 8, 30),
            observacoes = "Usuário desistiu da reserva"
        ),
        Reserva(
            id = "r4", usuarioId = "u1", jogoId = "10",
            dataReserva = LocalDate(2026, 9, 1), status = StatusReserva.EXPIRADA
        ),
    )
}

class ReservaRepositoryFake : ReservaRepository {
    private val reservas = ReservasFake.todas.toMutableList()

    override suspend fun listar(): List<Reserva> {
        delay(800)
        return reservas.toList()
    }

    override suspend fun criar(reserva: Reserva) {
        delay(300)
        reservas.add(reserva)
    }

    override suspend fun deletar(id: String) {
        delay(300)
        val removeu = reservas.removeAll { it.id == id }
        if (!removeu) throw AppException("Reserva não encontrada")
    }

    override suspend fun atualizar(reserva: Reserva) {
        delay(300)
        val indice = reservas.indexOfFirst { it.id == reserva.id }
        if (indice == -1) throw AppException("Reserva não encontrada")
        reservas[indice] = reserva
    }

    override suspend fun buscarPorId(id: String): Reserva {
        delay(300)
        return reservas.find { it.id == id } ?: throw AppException("Reserva não encontrada")
    }
}
