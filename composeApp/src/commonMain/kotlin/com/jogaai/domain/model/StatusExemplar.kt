package com.jogaai.domain.model

enum class StatusExemplar(val descricao: String) {
    DISPONIVEL("Disponível"),
    EMPRESTADO("Emprestado"),
    RESERVADO("Reservado"),
    MANUTENCAO("Manutenção"),
    INDISPONIVEL("Indisponível")
}