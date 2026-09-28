package com.jogaai.data

import com.jogaai.data.repository.AuthRepository
import com.jogaai.data.repository.AuthRepositoryFake
import com.jogaai.data.repository.CategoriaRepository
import com.jogaai.data.repository.CategoriaRepositoryFake
import com.jogaai.data.repository.EmprestimoRepository
import com.jogaai.data.repository.EmprestimoRepositoryFake
import com.jogaai.data.repository.ExemplarRepository
import com.jogaai.data.repository.ExemplarRepositoryFake
import com.jogaai.data.repository.JogoRepository
import com.jogaai.data.repository.JogoRepositoryFake
import com.jogaai.data.repository.ReservaRepository
import com.jogaai.data.repository.ReservaRepositoryFake
import com.jogaai.data.repository.UserRepository
import com.jogaai.data.repository.UserRepositoryFake

object AppModule {
    val authRepository: AuthRepository = AuthRepositoryFake()
    val jogoRepository: JogoRepository = JogoRepositoryFake()
    val exemplarRepository: ExemplarRepository = ExemplarRepositoryFake()
    val categoriaRepository: CategoriaRepository = CategoriaRepositoryFake()
    val userRepository: UserRepository = UserRepositoryFake()
    val emprestimoRepository: EmprestimoRepository = EmprestimoRepositoryFake()
    val reservaRepository: ReservaRepository = ReservaRepositoryFake()

}