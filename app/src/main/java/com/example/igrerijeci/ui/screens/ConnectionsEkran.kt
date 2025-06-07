package com.example.igrerijeci.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.igrerijeci.viewmodel.ConnectionsViewModel
import com.example.igrerijeci.navigation.Screens
import com.example.igrerijeci.ui.components.ConnectionsDodajGrupuPopup
import com.example.igrerijeci.ui.components.ConnectionsRezultatPopup

@Composable
fun ConnectionsEkran(
    viewModel: ConnectionsViewModel = viewModel(),
    navController: NavHostController
) {
    val rijeci = viewModel.words
    val preostaloPokusaja = 4 - viewModel.brojPokusaja
    val showRezultat = viewModel.igraGotova
    var showAddGroupPopup by remember { mutableStateOf(false) }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB33951))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 72.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "C O N N E C T I O N S",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF1F7ED), shape = RoundedCornerShape(8.dp))
                    .padding(16.dp)
            ) {
                LazyVerticalGrid(columns = GridCells.Fixed(4), modifier = Modifier.fillMaxWidth()) {
                    items(rijeci) { word ->
                        Box(
                            modifier = Modifier
                                .padding(8.dp)
                                .aspectRatio(2.2f)
                                .clip(RoundedCornerShape(8.dp))
                                .background(
                                    when {
                                        word.isFound -> Color(0xFF91C7B1)
                                        word.isSelected -> Color(0xFFE3D081)
                                        else -> Color.White
                                    }
                                )
                                .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
                                .clickable { viewModel.toggleSelection(word) },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = word.text,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.Black,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(4.dp)
                                    .fillMaxWidth()
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .background(Color(0xFFF1F7ED), shape = RoundedCornerShape(8.dp))
                    .padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Preostalo pokušaja: $preostaloPokusaja",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFFB33951),
                    textAlign = TextAlign.Center
                )
            }

        }
        Spacer(modifier = Modifier.height(16.dp))


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(50.dp)
                .background(Color(0xFFF1F7ED), shape = RoundedCornerShape(8.dp)),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {  }) {
                Icon(
                    imageVector = Icons.Default.SportsEsports,
                    contentDescription = "Igrica",
                    tint = Color(0xFFB33951)
                )
            }

            IconButton(onClick = { showAddGroupPopup = true }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Dodaj riječ",
                    tint = Color(0xFFB33951)
                )
            }

            IconButton(onClick = {
                navController.navigate(Screens.Pocetni.route)
            }) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Povratak na početnu stranicu",
                    tint = Color(0xFFB33951)
                )
            }

            IconButton(onClick = {
                navController.navigate(Screens.ConnectionsPravila.route)
            }) {
                Icon(
                    imageVector = Icons.Default.HelpOutline,
                    contentDescription = "Pravila",
                    tint = Color(0xFFB33951)
                )
            }

        }

        if (showRezultat) {
            ConnectionsRezultatPopup(
                isWin = viewModel.brojPronadjenihGrupa == 4,
                onRestart = {
                    viewModel.restartGame()
                },
                onHome = {
                    navController.navigate("pocetni") {
                        popUpTo("connections") { inclusive = true }
                    }
                }
            )
        }

        ConnectionsDodajGrupuPopup(
            show = showAddGroupPopup,
            onDismiss = { showAddGroupPopup = false },
            onSubmit = { grupa -> println("Predložena grupa: $grupa") }
        )

    }
}


