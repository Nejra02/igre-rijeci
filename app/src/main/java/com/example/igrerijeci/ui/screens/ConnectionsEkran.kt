package com.example.igrerijeci.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun ConnectionsEkran() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Connections igra uskoro...", fontSize = 20.sp)
    }
}