package com.example.igrerijeci.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.igrerijeci.data.wordleRijeci
import com.example.igrerijeci.model.SlovoState
import com.example.igrerijeci.navigation.Screens
import com.example.igrerijeci.ui.components.WordleDodajRijecPopup
import com.example.igrerijeci.ui.components.WordleRezultatPopup
import com.example.igrerijeci.viewmodel.WordleViewModel

@Composable
fun WordleEkran(
    viewModel: WordleViewModel = viewModel(),
    onPravilaClick: () -> Unit = {},
    navController: NavHostController
) {
    val context = LocalContext.current
    val board = viewModel.board
    var showPopup by remember { mutableStateOf(false) }
    var showAddPopup by remember { mutableStateOf(false) }

    LaunchedEffect(viewModel.igraGotova) {
        if (viewModel.igraGotova) {
            showPopup = true
        }
    }

    LaunchedEffect(viewModel.rijecNijeValidna) {
        if (viewModel.rijecNijeValidna) {
            Toast.makeText(context, "Riječ nije u listi!", Toast.LENGTH_SHORT).show()
            viewModel.resetujValidaciju()
        }
    }


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
                text = "W O R D L E",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF1F7ED), shape = RoundedCornerShape(8.dp))
                    .padding(16.dp)
            ) {
                for ((i, row) in board.withIndex()) {
                    val redJeAktivan = i == viewModel.red
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        val focusManager = LocalFocusManager.current
                        val redFokusLista = remember { List(5) { FocusRequester() } }

                        for ((j, polje) in row.withIndex()) {
                            val focusRequester = redFokusLista[j]
                            val backgroundColor = when (polje.state) {
                                SlovoState.Tacno -> Color(0xFF91C7B1)
                                SlovoState.PogresnoMjesto -> Color(0xFFE3D081)
                                SlovoState.Netacno -> Color.White
                                null -> Color.White
                            }

                            TextField(
                                value = polje.slovo,
                                onValueChange = { novo ->
                                    if (redJeAktivan) {
                                        viewModel.updatePolje(i, j, novo)
                                        if (novo.length == 1 && j < 4) {
                                            redFokusLista[j + 1].requestFocus()
                                        } else if (novo.length == 1 && j == 4) {
                                            focusManager.clearFocus()
                                        }
                                    }
                                },
                                readOnly = !redJeAktivan,
                                singleLine = true,
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier
                                    .size(56.dp)
                                    .focusRequester(focusRequester),
                                textStyle = TextStyle(
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                ),
                                colors = TextFieldDefaults.colors(
                                    focusedContainerColor = backgroundColor,
                                    unfocusedContainerColor = backgroundColor,
                                    disabledContainerColor = backgroundColor,
                                    cursorColor = Color.Black,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    disabledIndicatorColor = Color.Transparent
                                )
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { viewModel.submitRijec() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF54494B),
                    contentColor = Color.White
                )
            ) {
                Text("Potvrdi unos", fontSize = 17.sp)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(50.dp)
                .background(Color(0xFFF1F7ED), shape = RoundedCornerShape(8.dp)),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Default.SportsEsports,
                    contentDescription = "Igrica",
                    tint = Color(0xFFB33951)
                )
            }

            IconButton(onClick = { showAddPopup = true }) {
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

            IconButton(onClick = onPravilaClick) {
                Icon(
                    imageVector = Icons.Default.HelpOutline,
                    contentDescription = "Pravila",
                    tint = Color(0xFFB33951)
                )
            }
        }

        if (showPopup) {
            WordleRezultatPopup(
                board = viewModel.board,
                targetWord = viewModel.targetWordPublic,
                isWin = viewModel.pogodjena,
                onRestart = {
                    viewModel.restartGame()
                    showPopup = false
                },
                onHome = {
                    showPopup = false
                    navController.navigate("pocetni") {
                        popUpTo("wordle") { inclusive = true }
                    }
                }
            )
        }

        WordleDodajRijecPopup(
            show = showAddPopup,
            onDismiss = { showAddPopup = false },
            onSubmit = { novaRijec ->
                wordleRijeci.add(novaRijec)
            }
        )
    }
}
