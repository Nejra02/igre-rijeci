package com.example.igrerijeci

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.igrerijeci.ui.theme.IgreRijeciTheme
import androidx.navigation.compose.rememberNavController
import com.example.igrerijeci.navigation.AppNavigation


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            setContent {
                IgreRijeciTheme {
                    val navController = rememberNavController()
                    AppNavigation(navController = navController)

                }
            }

        }
    }
}