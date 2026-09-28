package com.jogaai.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Casino
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Games
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.ui.graphics.vector.ImageVector

enum class Section(
    val label: String,
    val icon: ImageVector
) {
    Panel("Painel", Icons.Default.Dashboard),
    Games("Jogos", Icons.Default.Casino),
    Copies("Exemplares", Icons.Default.Inventory2),
    Categories("Categorias", Icons.Default.LocalOffer),
    Loans("Empréstimos", Icons.Default.SwapHoriz),
    Reservations("Reservas", Icons.Default.Schedule),
    People("Pessoas", Icons.Default.Groups);

    companion object {
        val principais = listOf(Panel, Games, Loans, Reservations, People)
    }
}