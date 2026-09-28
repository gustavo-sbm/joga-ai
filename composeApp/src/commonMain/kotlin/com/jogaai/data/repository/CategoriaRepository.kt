package com.jogaai.data.repository

import com.jogaai.domain.model.Categoria
import com.jogaai.domain.model.Jogo

interface CategoriaRepository {
    suspend fun listar(): List<Categoria>
    suspend fun criar(categoria: Categoria)
    suspend fun deletar(id: String)
    suspend fun atualizar(categoria: Categoria)
    suspend fun buscarPorId(id: String): Categoria
}