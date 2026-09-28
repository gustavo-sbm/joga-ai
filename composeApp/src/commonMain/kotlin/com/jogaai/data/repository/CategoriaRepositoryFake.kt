package com.jogaai.data.repository

import com.jogaai.data.exception.AppException
import com.jogaai.domain.model.Categoria
import kotlinx.coroutines.delay


object CategoriasFake {
    val estrategia  = Categoria("1", "Estratégia",  "Decisões de longo prazo e pouca sorte")
    val familia     = Categoria("2", "Família",     "Regras simples, partidas curtas")
    val cooperativo = Categoria("3", "Cooperativo", "Todos jogam contra o jogo")
    val party       = Categoria("4", "Party game",  "Grupos grandes e clima descontraído")
    val cartas      = Categoria("5", "Cartas",      "Baralho como componente principal")
    val eurogame    = Categoria("6", "Eurogame",    "Gestão de recursos e pontuação")
    val deducao     = Categoria("7", "Dedução",     "Pistas, blefe e leitura de intenção")

    val todas = listOf(estrategia, familia, cooperativo, party, cartas, eurogame, deducao)
}

class CategoriaRepositoryFake : CategoriaRepository {
    private val categorias = CategoriasFake.todas.toMutableList()

    override suspend fun listar(): List<Categoria> {
        delay(800)
        return categorias.toList()
    }

    override suspend fun criar(categoria: Categoria) {
        delay(300)
        categorias.add(categoria)
    }

    override suspend fun deletar(id: String) {
        delay(300)
        val removeu = categorias.removeAll { it.id == id }
        if (!removeu) throw AppException("Categoria não encontrada")
    }

    override suspend fun atualizar(categoria: Categoria) {
        delay(300)
        val indice = categorias.indexOfFirst { it.id == categoria.id }
        if (indice == -1) throw AppException("Categoria não encontrada")
        categorias[indice] = categoria
    }

    override suspend fun buscarPorId(id: String): Categoria {
        delay(300)
        return categorias.find { it.id == id } ?: throw AppException("Categoria não encontrada")
    }
}
