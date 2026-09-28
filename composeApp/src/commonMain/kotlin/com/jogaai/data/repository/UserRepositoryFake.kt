package com.jogaai.data.repository

import com.jogaai.data.exception.AppException
import com.jogaai.domain.model.StatusUser
import com.jogaai.domain.model.User
import kotlinx.coroutines.delay
import kotlinx.datetime.LocalDate


object UsuariosFake {
    val todos = listOf(
        User(
            id = "u1", nome = "Ana Beatriz Souza", email = "ana.souza@exemplo.com",
            telefone = "(67) 99101-0001", status = StatusUser.ATIVO,
            dataNascimento = LocalDate(2003, 4, 12), dataCadastro = LocalDate(2026, 2, 10)
        ),
        User(
            id = "u2", nome = "Bruno Carvalho", email = "bruno.carvalho@exemplo.com",
            telefone = "(67) 99101-0002", status = StatusUser.ATIVO,
            dataNascimento = LocalDate(2001, 11, 3), dataCadastro = LocalDate(2026, 2, 14)
        ),
        User(
            id = "u3", nome = "Camila Rocha", email = "camila.rocha@exemplo.com",
            telefone = "(67) 99101-0003", status = StatusUser.ATIVO,
            dataNascimento = LocalDate(2004, 7, 25), dataCadastro = LocalDate(2026, 3, 2)
        ),
        User(
            id = "u4", nome = "Diego Martins", email = "diego.martins@exemplo.com",
            telefone = "(67) 99101-0004", status = StatusUser.ATIVO,
            dataNascimento = LocalDate(2000, 1, 30), dataCadastro = LocalDate(2026, 3, 18)
        ),
        User(
            id = "u5", nome = "Eduarda Lima", email = "eduarda.lima@exemplo.com",
            telefone = "(67) 99101-0005", status = StatusUser.ATIVO,
            dataNascimento = LocalDate(2005, 9, 8), dataCadastro = LocalDate(2026, 4, 7)
        ),
        User(
            id = "u6", nome = "Felipe Andrade", email = "felipe.andrade@exemplo.com",
            telefone = "(67) 99101-0006", status = StatusUser.BLOQUEADO,
            dataNascimento = LocalDate(2002, 5, 19), dataCadastro = LocalDate(2026, 4, 21),
            dataAlteracao = LocalDate(2026, 8, 3)
        ),
        User(
            id = "u7", nome = "Gabriela Nunes", email = "gabriela.nunes@exemplo.com",
            telefone = "(67) 99101-0007", status = StatusUser.INATIVO,
            dataNascimento = LocalDate(2003, 12, 1), dataCadastro = LocalDate(2026, 5, 5),
            dataAlteracao = LocalDate(2026, 7, 30)
        ),
    )
}

class UserRepositoryFake : UserRepository {
    private val usuarios = UsuariosFake.todos.toMutableList()

    override suspend fun listar(): List<User> {
        delay(800)
        return usuarios.toList()
    }

    override suspend fun criar(user: User) {
        delay(300)
        usuarios.add(user)
    }

    override suspend fun deletar(id: String) {
        delay(300)
        val removeu = usuarios.removeAll { it.id == id }
        if (!removeu) throw AppException("Usuário não encontrado")
    }

    override suspend fun atualizar(user: User) {
        delay(300)
        val indice = usuarios.indexOfFirst { it.id == user.id }
        if (indice == -1) throw AppException("Usuário não encontrado")
        usuarios[indice] = user
    }

    override suspend fun buscarPorId(id: String): User {
        delay(300)
        return usuarios.find { it.id == id } ?: throw AppException("Usuário não encontrado")
    }
}
