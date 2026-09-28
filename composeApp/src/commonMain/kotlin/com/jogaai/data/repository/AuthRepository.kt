package com.jogaai.data.repository

interface AuthRepository {
    suspend fun login(email: String, password: String)
    suspend fun register(nome: String, email: String, password: String)
}