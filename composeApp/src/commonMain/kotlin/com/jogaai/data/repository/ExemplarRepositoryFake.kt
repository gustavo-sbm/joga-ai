package com.jogaai.data.repository

import com.jogaai.data.exception.AppException
import com.jogaai.domain.model.EstadoConservacao
import com.jogaai.domain.model.Exemplar
import com.jogaai.domain.model.StatusExemplar
import kotlinx.coroutines.delay

object ExemplaresFake {
    val todos = listOf(
        // Catan
        Exemplar("1-001", "001", "1", StatusExemplar.EMPRESTADO,  EstadoConservacao.BOM),
        Exemplar("1-002", "002", "1", StatusExemplar.DISPONIVEL,  EstadoConservacao.EXCELENTE),
        Exemplar("1-003", "003", "1", StatusExemplar.MANUTENCAO,  EstadoConservacao.DANIFICADO,
            "Tabuleiro descolando na dobra central"),
        // Azul
        Exemplar("2-001", "001", "2", StatusExemplar.DISPONIVEL,  EstadoConservacao.BOM),
        Exemplar("2-002", "002", "2", StatusExemplar.EMPRESTADO,  EstadoConservacao.NOVO),
        // Wingspan
        Exemplar("3-001", "001", "3", StatusExemplar.EMPRESTADO,  EstadoConservacao.EXCELENTE),
        // Dixit
        Exemplar("4-001", "001", "4", StatusExemplar.DISPONIVEL,  EstadoConservacao.BOM),
        Exemplar("4-002", "002", "4", StatusExemplar.EMPRESTADO,  EstadoConservacao.BOM),
        Exemplar("4-003", "003", "4", StatusExemplar.DISPONIVEL,  EstadoConservacao.REGULAR,
            "Caixa com desgaste nas quinas"),
        // Ticket to Ride
        Exemplar("5-001", "001", "5", StatusExemplar.EMPRESTADO,  EstadoConservacao.BOM),
        Exemplar("5-002", "002", "5", StatusExemplar.DISPONIVEL,  EstadoConservacao.EXCELENTE),
        // Carcassonne
        Exemplar("6-001", "001", "6", StatusExemplar.DISPONIVEL,  EstadoConservacao.BOM),
        Exemplar("6-002", "002", "6", StatusExemplar.DISPONIVEL,  EstadoConservacao.REGULAR),
        // 7 Wonders Duel
        Exemplar("7-001", "001", "7", StatusExemplar.DISPONIVEL,  EstadoConservacao.NOVO),
        // Terraforming Mars
        Exemplar("8-001", "001", "8", StatusExemplar.EMPRESTADO,  EstadoConservacao.BOM),
        Exemplar("8-002", "002", "8", StatusExemplar.INDISPONIVEL, EstadoConservacao.REGULAR,
            "Faltam 4 cubos de produção"),
        // Pandemic
        Exemplar("9-001", "001", "9", StatusExemplar.EMPRESTADO,  EstadoConservacao.BOM),
        Exemplar("9-002", "002", "9", StatusExemplar.RESERVADO,   EstadoConservacao.BOM),
        // Codenames
        Exemplar("10-001", "001", "10", StatusExemplar.EMPRESTADO, EstadoConservacao.BOM),
        Exemplar("10-002", "002", "10", StatusExemplar.DISPONIVEL, EstadoConservacao.BOM),
        Exemplar("10-003", "003", "10", StatusExemplar.DISPONIVEL, EstadoConservacao.NOVO)
    )

    fun porId(id: String): Exemplar = todos.first { it.id == id }
}

class ExemplarRepositoryFake : ExemplarRepository {
    private val exemplares = ExemplaresFake.todos.toMutableList()

    override suspend fun listar(): List<Exemplar> {
        delay(800)
        return exemplares.toList()
    }

    override suspend fun criar(exemplar: Exemplar) {
        delay(300)
        exemplares.add(exemplar)
    }

    override suspend fun deletar(id: String) {
        delay(300)
        val removeu = exemplares.removeAll { it.id == id }
        if (!removeu) throw AppException("Exemplar não encontrado")
    }

    override suspend fun atualizar(exemplar: Exemplar) {
        delay(300)
        val indice = exemplares.indexOfFirst { it.id == exemplar.id }
        if (indice == -1) throw AppException("Exemplar não encontrado")
        exemplares[indice] = exemplar
    }

    override suspend fun buscarPorId(id: String): Exemplar {
        delay(300)
        return exemplares.find { it.id == id } ?: throw AppException("Exemplar não encontrado")
    }
}
