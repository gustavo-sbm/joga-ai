package com.jogaai.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jogaai.ui.categoria.CategoriaScreen
import com.jogaai.ui.emprestimo.EmprestimoScreen
import com.jogaai.ui.exemplar.ExemplarScreen
import com.jogaai.ui.jogo.JogoScreen
import com.jogaai.ui.painel.PainelScreen
import com.jogaai.ui.pessoa.PessoaScreen
import com.jogaai.ui.reserva.ReservaScreen
import com.jogaai.ui.shared.AppScaffold

@Composable
fun MainNavigation(onLogout: () -> Unit) {
    val navController = rememberNavController()
    var selectedSection by remember { mutableStateOf(Section.Panel) }

    AppScaffold(
        selectedSection = selectedSection,
        nomeUsuario = "Coordenação",
        papelUsuario = "Administração do Acervo",
        onLogout = onLogout,
        onSectionSelected = { section ->
            selectedSection = section
            val rota = when(section) {
                Section.Panel -> Panel
                Section.Games -> Game
                Section.Copies -> Copies
                Section.Categories -> Categories
                Section.Loans -> Loans
                Section.Reservations -> Reservations
                Section.People -> People
            }

            navController.navigate(rota) {
                popUpTo(Panel) { saveState = true  }
                launchSingleTop = true
                restoreState = true
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = Panel,
            enterTransition = { valEnterTransition },
            exitTransition = { navExitTransition },
            popEnterTransition = { valEnterTransition },
            popExitTransition = { navExitTransition }) {
            composable<Panel> { PainelScreen() }
            composable<Game> {
                JogoScreen()
            }
            composable<Copies> { ExemplarScreen() }
            composable<Categories> { CategoriaScreen() }
            composable<Loans> { EmprestimoScreen() }
            composable<Reservations> { ReservaScreen() }
            composable<People> { PessoaScreen() }
        }
    }
}