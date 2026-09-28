package com.jogaai.domain.model

enum class StatusEmprestimo(val description: String) {
    ATIVO("Ativo"),
    ATRASADO("Atrasado"),
    FINALIZADO("Finalizado"),
    CANCELADO("Cancelado"),
}