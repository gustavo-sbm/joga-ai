package com.jogaai.data.repository

import com.jogaai.data.exception.AppException
import com.jogaai.domain.model.Emprestimo
import com.jogaai.domain.model.StatusEmprestimo
import kotlinx.coroutines.delay
import kotlinx.datetime.LocalDate


object EmprestimosFake {
    val todos = listOf(
        // Ativos
        Emprestimo(
            id = "e1", usuarioId = "u1",
            dataEmprestimo = LocalDate(2026, 9, 20), dataPrevistaDevolucao = LocalDate(2026, 10, 4),
            status = StatusEmprestimo.ATIVO,
            exemplares = listOf(ExemplaresFake.porId("1-001"), ExemplaresFake.porId("4-002")),
            dataCadastro = LocalDate(2026, 9, 20)
        ),
        Emprestimo(
            id = "e2", usuarioId = "u2",
            dataEmprestimo = LocalDate(2026, 9, 22), dataPrevistaDevolucao = LocalDate(2026, 10, 6),
            status = StatusEmprestimo.ATIVO,
            exemplares = listOf(ExemplaresFake.porId("2-002")),
            dataCadastro = LocalDate(2026, 9, 22)
        ),
        Emprestimo(
            id = "e3", usuarioId = "u3",
            dataEmprestimo = LocalDate(2026, 9, 25), dataPrevistaDevolucao = LocalDate(2026, 10, 9),
            status = StatusEmprestimo.ATIVO,
            exemplares = listOf(ExemplaresFake.porId("3-001"), ExemplaresFake.porId("10-001")),
            dataCadastro = LocalDate(2026, 9, 25),
            observacoes = "Retirada feita a partir da reserva r2"
        ),
        Emprestimo(
            id = "e5", usuarioId = "u5",
            dataEmprestimo = LocalDate(2026, 9, 24), dataPrevistaDevolucao = LocalDate(2026, 10, 8),
            status = StatusEmprestimo.ATIVO,
            exemplares = listOf(ExemplaresFake.porId("8-001"), ExemplaresFake.porId("9-001")),
            dataCadastro = LocalDate(2026, 9, 24)
        ),
        // Atrasado
        Emprestimo(
            id = "e4", usuarioId = "u4",
            dataEmprestimo = LocalDate(2026, 9, 1), dataPrevistaDevolucao = LocalDate(2026, 9, 15),
            status = StatusEmprestimo.ATRASADO,
            exemplares = listOf(ExemplaresFake.porId("5-001")),
            dataCadastro = LocalDate(2026, 9, 1),
            observacoes = "Usuário avisado por e-mail em 16/09",
            dataAlteracao = LocalDate(2026, 9, 16)
        ),
        // Histórico
        Emprestimo(
            id = "e6", usuarioId = "u2",
            dataEmprestimo = LocalDate(2026, 8, 10), dataPrevistaDevolucao = LocalDate(2026, 8, 24),
            status = StatusEmprestimo.FINALIZADO,
            exemplares = listOf(ExemplaresFake.porId("6-001")),
            dataCadastro = LocalDate(2026, 8, 10),
            dataDevolucao = LocalDate(2026, 8, 22),
            dataAlteracao = LocalDate(2026, 8, 22)
        ),
        Emprestimo(
            id = "e7", usuarioId = "u6",
            dataEmprestimo = LocalDate(2026, 7, 1), dataPrevistaDevolucao = LocalDate(2026, 7, 15),
            status = StatusEmprestimo.FINALIZADO,
            exemplares = listOf(ExemplaresFake.porId("7-001")),
            dataCadastro = LocalDate(2026, 7, 1),
            dataDevolucao = LocalDate(2026, 8, 2),
            observacoes = "Devolvido com 18 dias de atraso; usuário bloqueado",
            dataAlteracao = LocalDate(2026, 8, 2)
        ),
        Emprestimo(
            id = "e8", usuarioId = "u3",
            dataEmprestimo = LocalDate(2026, 9, 5), dataPrevistaDevolucao = LocalDate(2026, 9, 19),
            status = StatusEmprestimo.CANCELADO,
            exemplares = listOf(ExemplaresFake.porId("1-002")),
            dataCadastro = LocalDate(2026, 9, 5),
            observacoes = "Cancelado a pedido do usuário antes da retirada",
            dataAlteracao = LocalDate(2026, 9, 5)
        ),
    )
}

class EmprestimoRepositoryFake : EmprestimoRepository {
    private val emprestimos = EmprestimosFake.todos.toMutableList()

    override suspend fun listar(): List<Emprestimo> {
        delay(800)
        return emprestimos.toList()
    }

    override suspend fun criar(emprestimo: Emprestimo) {
        delay(300)
        emprestimos.add(emprestimo)
    }

    override suspend fun deletar(id: String) {
        delay(300)
        val removeu = emprestimos.removeAll { it.id == id }
        if (!removeu) throw AppException("Empréstimo não encontrado")
    }

    override suspend fun atualizar(emprestimo: Emprestimo) {
        delay(300)
        val indice = emprestimos.indexOfFirst { it.id == emprestimo.id }
        if (indice == -1) throw AppException("Empréstimo não encontrado")
        emprestimos[indice] = emprestimo
    }

    override suspend fun buscarPorId(id: String): Emprestimo {
        delay(300)
        return emprestimos.find { it.id == id } ?: throw AppException("Empréstimo não encontrado")
    }
}
