package com.jogaai.data.repository

import com.jogaai.data.exception.AppException
import kotlinx.coroutines.delay

class AuthRepositoryFake : AuthRepository {
    override suspend fun login(email: String, password: String) {
        delay(2000)
        if (password != "12345678") {
            throw AppException("E-mail ou senha inválidos")
        }
    }

    override suspend fun register(nome: String, email: String, password: String) {
    }
}