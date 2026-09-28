package com.jogaai.theme

import androidx.compose.ui.graphics.Color
import com.jogaai.domain.model.StatusEmprestimo
import com.jogaai.domain.model.StatusExemplar
import com.jogaai.domain.model.StatusReserva
import com.jogaai.domain.model.StatusUser

val CoresDeTampa = listOf(
    Color(0xFF2C6CB0),  // azul
    Color(0xFF2B4E6E),  // azul-noite
    Color(0xFF1F6F78),  // petróleo
    Color(0xFF2F7A6B),  // esmeralda
    Color(0xFF2E7D5B),  // verde
    Color(0xFF4A6B23),  // folha
    Color(0xFF8A6B1F),  // mostarda
    Color(0xFF9C5A1F),  // âmbar escuro
    Color(0xFFA9541F),  // terracota
    Color(0xFFC2372A),  // vermelho
    Color(0xFF7D2E4E),  // vinho
    Color(0xFF6B4C9A)   // roxo
)

fun corDaTampa(nome: String): Color =
    CoresDeTampa[nome.sumOf { it.code } % CoresDeTampa.size]

val VerdeDisponivel = Color(0xFF287050)
val AzulEmprestado  = Color(0xFF2C6CB0)
val AmbarReservado  = Color(0xFF8C6108)
val VermelhoAlerta  = Color(0xFFC2372A)
val CinzaInativo    = Color(0xFF646C6A)

fun corDoStatus(status: StatusExemplar): Color = when (status) {
    StatusExemplar.DISPONIVEL   -> VerdeDisponivel
    StatusExemplar.EMPRESTADO   -> AzulEmprestado
    StatusExemplar.RESERVADO    -> AmbarReservado
    StatusExemplar.MANUTENCAO   -> VermelhoAlerta
    StatusExemplar.INDISPONIVEL -> CinzaInativo
}

fun corDoStatus(status: StatusUser): Color = when (status) {
    StatusUser.ATIVO     -> VerdeDisponivel
    StatusUser.INATIVO   -> CinzaInativo
    StatusUser.BLOQUEADO -> VermelhoAlerta
}

fun corDoStatus(status: StatusEmprestimo): Color = when (status) {
    StatusEmprestimo.ATIVO      -> AzulEmprestado
    StatusEmprestimo.ATRASADO   -> VermelhoAlerta
    StatusEmprestimo.FINALIZADO -> VerdeDisponivel
    StatusEmprestimo.CANCELADO  -> CinzaInativo
}

fun corDoStatus(status: StatusReserva): Color = when (status) {
    StatusReserva.ATIVA     -> AmbarReservado
    StatusReserva.ATENDIDA  -> VerdeDisponivel
    StatusReserva.CANCELADA -> CinzaInativo
    StatusReserva.EXPIRADA  -> CinzaInativo
}

// Teal — cor principal
val Teal50 = Color(0xFFE1F5EE)
val Teal100 = Color(0xFF9FE1CB)
val Teal400 = Color(0xFF1D9E75)
val Teal600 = Color(0xFF0F6E56)
val Teal900 = Color(0xFF04342C)

// Neutros
val Neutral0 = Color(0xFFFFFFFF)
val NeutralBg = Color(0xFFF7F7F5)
val Neutral200 = Color(0xFFD3D1C7)
val Neutral400 = Color(0xFF888780)
val Neutral600 = Color(0xFF5F5E5A)
val Neutral900 = Color(0xFF1C1C1A)

// Status
val Blue50 = Color(0xFFE6F1FB)
val Blue400 = Color(0xFF378ADD)
val Blue600 = Color(0xFF185FA5)
val Blue900 = Color(0xFF042C53)

val Coral50 = Color(0xFFFAECE7)
val Coral400 = Color(0xFFD85A30)
val Coral600 = Color(0xFF993C1D)
val Coral900 = Color(0xFF4A1B0C)

val Gray50 = Color(0xFFF1EFE8)
val NeutralBgDark = Color(0xFF161614)
val NeutralSurfaceDark = Color(0xFF232320)