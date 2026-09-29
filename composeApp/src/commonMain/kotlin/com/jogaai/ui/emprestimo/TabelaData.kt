package com.jogaai.ui.emprestimo

import androidx.compose.runtime.Composable

data class TabelaColuna<T>(val nome: String, val peso: Float, val conteudo: @Composable (value: T) -> Unit)
data class Tabela<T>(val colunas: List<TabelaColuna<T>>, val linhas: List<T>)