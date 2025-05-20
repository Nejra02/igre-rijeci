package com.example.igrerijeci.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.igrerijeci.ui.screens.ConnectionsEkran
import com.example.igrerijeci.ui.screens.PocetniEkran
import com.example.igrerijeci.ui.screens.WordleEkran

sealed class Screens(val route: String) {
    object Pocetni : Screens("pocetni")
    object Wordle : Screens("wordle")
    object Connections : Screens("connections")
    object Treca : Screens("treca")
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.Pocetni.route) {
        composable(Screens.Pocetni.route) { PocetniEkran(navController = navController) }
        composable(Screens.Wordle.route) { WordleEkran() }
        composable(Screens.Connections.route) { ConnectionsEkran() }
    }
}
