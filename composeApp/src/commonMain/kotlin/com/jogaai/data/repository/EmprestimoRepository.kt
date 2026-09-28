package com.jogaai.data.repository

import com.jogaai.domain.model.Emprestimo


interface EmprestimoRepository {
    suspend fun listar(): List<Emprestimo>
    suspend fun criar(emprestimo: Emprestimo)
    suspend fun deletar(id: String)
    suspend fun atualizar(emprestimo: Emprestimo)
    suspend fun buscarPorId(id: String): Emprestimo
}