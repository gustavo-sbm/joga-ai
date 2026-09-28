package com.jogaai.domain.model

data class Jogo(
    val id: String,
    val nome: String,
    val descricao: String,
    val editora: String?,
    val anoLancamento: Int?,
    val minJogadores: Int,
    val maxJogadores: Int,
    val idadeMinima: Int,
    val minDuracao: Int,
    val maxDuracao: Int,
    val categorias: List<Categoria>,
    val ativo: Boolean
)
