package com.example.igrerijeci.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun PravilaEkran(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB33951))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF1F7ED))
                .padding(horizontal = 24.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                text = "Pravila igre",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top=40.dp),
                color = Color(0xFFB33951)
            )

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Pogodi riječ u 6 pokušaja. Nakon svakog pokušaja, boje na ploči će se promijeniti da ti pokažu koliko si blizu pravoj riječi.",
                fontSize = 15.sp,
                color = Color.DarkGray,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()

            )

            // tačno slovo
            Row(verticalAlignment = Alignment.CenterVertically) {
                ExampleLetterBox("A", Color(0xFF91C7B1))
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Riječ sadrži slovo A i na pravom je mjestu.",
                    fontSize = 15.sp,
                    color = Color.DarkGray
                )
            }

            // pogrešno mjesto
            Row(verticalAlignment = Alignment.CenterVertically) {
                ExampleLetterBox("E", Color(0xFFE3D081))
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Riječ sadrži slovo E, ali je na pogrešnom mjestu.",
                    fontSize = 15.sp,
                    color = Color.DarkGray
                )
            }

            // netačno slovo
            Row(verticalAlignment = Alignment.CenterVertically) {
                ExampleLetterBox("N", Color.White)
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Riječ ne sadrži slovo N.",
                    fontSize = 15.sp,
                    color = Color.DarkGray
                )
            }

        }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(50.dp)
                    .background(Color(0xFFB33951), shape = RoundedCornerShape(8.dp)),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.navigate("wordle") }) {
                    Icon(
                        imageVector = Icons.Default.SportsEsports,
                        contentDescription = "Igrica",
                        tint = Color(0xFFF1F7ED)
                    )
                }

                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.HelpOutline,
                        contentDescription = "Pravila",
                        tint = Color(0xFFF1F7ED)
                    )
                }

            }

    }
}

@Composable
fun ExampleLetterBox(slovo: String, boja: Color) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .background(boja, shape = RoundedCornerShape(6.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = slovo,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewPravilaEkran() {
    PravilaEkran(navController = rememberNavController())
}
