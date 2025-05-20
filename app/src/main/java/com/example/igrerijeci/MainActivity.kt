package com.example.igrerijeci

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.igrerijeci.ui.theme.IgreRijeciTheme
import com.example.igrerijeci.ui.screens.PocetniEkran
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