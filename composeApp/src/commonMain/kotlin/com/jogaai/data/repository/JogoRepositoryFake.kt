package com.jogaai.data.repository

import com.jogaai.data.exception.AppException
import com.jogaai.domain.model.Jogo
import kotlinx.coroutines.delay

class JogoRepositoryFake : JogoRepository {
    private val jogos = mutableListOf(
        Jogo(
            id = "1", nome = "Catan", editora = "Grow", anoLancamento = 1995,
            descricao = "Troque recursos com a mesa, ocupe cruzamentos e chegue a dez pontos antes dos outros colonos.",
            minJogadores = 3, maxJogadores = 4, idadeMinima = 10,
            minDuracao = 60, maxDuracao = 90,
            categorias = listOf(CategoriasFake.estrategia, CategoriasFake.familia, CategoriasFake.eurogame), ativo = true
        ),
        Jogo(
            id = "2", nome = "Azul", editora = "Galápagos", anoLancamento = 2017,
            descricao = "Colete azulejos, preencha as fileiras do palácio e evite sobras que viram penalidade.",
            minJogadores = 2, maxJogadores = 4, idadeMinima = 8,
            minDuracao = 30, maxDuracao = 45,
            categorias = listOf(CategoriasFake.estrategia, CategoriasFake.familia, CategoriasFake.eurogame), ativo = true
        ),
        Jogo(
            id = "3", nome = "Wingspan", editora = "Grok Games", anoLancamento = 2019,
            descricao = "Atraia aves para três habitats e encadeie os poderes das cartas ao longo de quatro rodadas.",
            minJogadores = 1, maxJogadores = 5, idadeMinima = 10,
            minDuracao = 40, maxDuracao = 70,
            categorias = listOf(CategoriasFake.estrategia, CategoriasFake.eurogame), ativo = true
        ),
        Jogo(
            id = "4", nome = "Dixit", editora = "Galápagos", anoLancamento = 2008,
            descricao = "Descreva a ilustração com uma frase ambígua o bastante para alguns acertarem, mas não todos.",
            minJogadores = 3, maxJogadores = 6, idadeMinima = 8,
            minDuracao = 30, maxDuracao = 30,
            categorias = listOf(CategoriasFake.party, CategoriasFake.cartas, CategoriasFake.deducao), ativo = true
        ),
        Jogo(
            id = "5", nome = "Ticket to Ride", editora = "Galápagos", anoLancamento = 2004,
            descricao = "Some cartas da mesma cor, tome as rotas do mapa e complete seus bilhetes de destino.",
            minJogadores = 2, maxJogadores = 5, idadeMinima = 8,
            minDuracao = 45, maxDuracao = 60,
            categorias = listOf(CategoriasFake.estrategia, CategoriasFake.familia), ativo = true
        ),
        Jogo(
            id = "6", nome = "Carcassonne", editora = "Devir", anoLancamento = 2000,
            descricao = "Encaixe as peças do mapa e posicione seus meeples para fechar cidades, estradas e mosteiros.",
            minJogadores = 2, maxJogadores = 5, idadeMinima = 7,
            minDuracao = 35, maxDuracao = 45,
            categorias = listOf(CategoriasFake.familia, CategoriasFake.eurogame), ativo = true
        ),
        Jogo(
            id = "7", nome = "7 Wonders Duel", editora = "Galápagos", anoLancamento = 2015,
            descricao = "Feito para dois: puxe cartas da pirâmide e vença por pontos, ciência ou avanço militar.",
            minJogadores = 2, maxJogadores = 2, idadeMinima = 10,
            minDuracao = 30, maxDuracao = 30,
            categorias = listOf(CategoriasFake.estrategia, CategoriasFake.cartas, CategoriasFake.eurogame), ativo = true
        ),
        Jogo(
            id = "8", nome = "Terraforming Mars", editora = "Meeple BR", anoLancamento = 2016,
            descricao = "Sua corporação eleva oxigênio, temperatura e oceanos até Marte ficar habitável.",
            minJogadores = 1, maxJogadores = 5, idadeMinima = 12,
            minDuracao = 90, maxDuracao = 150,
            categorias = listOf(CategoriasFake.estrategia, CategoriasFake.eurogame), ativo = true
        ),
        Jogo(
            id = "9", nome = "Pandemic", editora = "Devir", anoLancamento = 2008,
            descricao = "A equipe divide funções e corre para conter quatro doenças antes que os surtos se espalhem.",
            minJogadores = 2, maxJogadores = 4, idadeMinima = 8,
            minDuracao = 45, maxDuracao = 60,
            categorias = listOf(CategoriasFake.cooperativo, CategoriasFake.estrategia), ativo = true
        ),
        Jogo(
            id = "10", nome = "Codenames", editora = "Devir", anoLancamento = 2015,
            descricao = "Uma palavra e um número: a dica precisa ligar os agentes certos sem entregar o assassino.",
            minJogadores = 4, maxJogadores = 8, idadeMinima = 10,
            minDuracao = 15, maxDuracao = 20,
            categorias = listOf(CategoriasFake.party, CategoriasFake.cartas, CategoriasFake.deducao), ativo = true
        ),
        Jogo(
            id = "11", nome = "Everdell", editora = "Meeple BR", anoLancamento = 2018,
            descricao = "Construa uma cidade na floresta encaixando cartas de criaturas e construções ao longo de quatro estações.",
            minJogadores = 1, maxJogadores = 4, idadeMinima = 13,
            minDuracao = 40, maxDuracao = 80,
            categorias = listOf(CategoriasFake.estrategia, CategoriasFake.eurogame), ativo = false
        )
    )

    override suspend fun listar(): List<Jogo> {
        delay(800)
        return jogos.toList()
    }

    override suspend fun criar(jogo: Jogo) {
        delay(300)
        jogos.add(jogo)
    }

    override suspend fun deletar(id: String) {
        delay(300)
        val removeu = jogos.removeAll { it.id == id }
        if (!removeu) throw AppException("Jogo não encontrado")
    }

    override suspend fun atualizar(jogo: Jogo) {
        delay(300)
        val indice = jogos.indexOfFirst { it.id == jogo.id }
        if (indice == -1) throw AppException("Jogo não encontrado")
        jogos[indice] = jogo
    }

    override suspend fun buscarPorId(id: String): Jogo {
        delay(300)
        return jogos.find { it.id == id } ?: throw AppException("Jogo não encontrado")
    }
}
