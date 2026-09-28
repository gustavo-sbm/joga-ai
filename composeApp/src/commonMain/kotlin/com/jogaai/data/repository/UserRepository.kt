package com.jogaai.data.repository

import com.jogaai.domain.model.User

interface UserRepository{
    suspend fun listar(): List<User>
    suspend fun criar(user: User)
    suspend fun deletar(id: String)
    suspend fun atualizar(user: User)
    suspend fun buscarPorId(id: String): User
}