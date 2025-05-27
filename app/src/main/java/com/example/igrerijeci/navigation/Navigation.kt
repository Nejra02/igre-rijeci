package com.example.igrerijeci.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.igrerijeci.ui.screens.ConnectionsEkran
import com.example.igrerijeci.ui.screens.ConnectionsPravilaEkran
import com.example.igrerijeci.ui.screens.PocetniEkran
import com.example.igrerijeci.ui.screens.WordleEkran
import com.example.igrerijeci.ui.screens.PravilaEkran

sealed class Screens(val route: String) {
    object Pocetni : Screens("pocetni")
    object Wordle : Screens("wordle")
    object Connections : Screens("connections")
    object Pravila : Screens("pravila")
    object ConnectionsPravila : Screens("connections_pravila")

}


@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.Pocetni.route) {
        composable(Screens.Pocetni.route) {
            PocetniEkran(navController = navController)
        }
        composable(Screens.Wordle.route) {
            WordleEkran(
                navController = navController,
                onPravilaClick = {
                    navController.navigate(Screens.Pravila.route)
                }
            )
        }
        composable(Screens.Connections.route) {
            ConnectionsEkran(navController = navController)
        }
        composable(Screens.Pravila.route) {
            PravilaEkran(navController = navController)
        }
        composable(Screens.ConnectionsPravila.route) {
            ConnectionsPravilaEkran(navController = navController)
        }

    }
}
