package com.jogaai.data.repository

import com.jogaai.domain.model.Jogo

interface JogoRepository {
    suspend fun listar(): List<Jogo>
    suspend fun criar(jogo: Jogo)
    suspend fun deletar(id: String)
    suspend fun atualizar(jogo: Jogo)
    suspend fun buscarPorId(id: String): Jogo
}