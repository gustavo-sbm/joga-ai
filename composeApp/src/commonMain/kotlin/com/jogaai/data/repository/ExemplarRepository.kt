package com.jogaai.data.repository

import com.jogaai.domain.model.Exemplar

interface ExemplarRepository {
    suspend fun listar(): List<Exemplar>
    suspend fun criar(exemplar: Exemplar)
    suspend fun deletar(id: String)
    suspend fun atualizar(exemplar: Exemplar)
    suspend fun buscarPorId(id: String): Exemplar
}