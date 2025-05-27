package com.example.igrerijeci.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.igrerijeci.model.Polje

@Composable
fun WordleRezultatPopup(
    board: List<List<Polje>>,
    targetWord: String,
    isWin: Boolean,
    onRestart: () -> Unit,
    onHome: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.6f)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .background(Color(0xFFF1F7ED), shape = RoundedCornerShape(16.dp))
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = if (isWin) "Bravo! Ti rasturaš!" else "Šteta!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFB33951)
            )

            Spacer(modifier = Modifier.height(12.dp))

            if (!isWin) {
                Text(
                    text = "Trebala se pogoditi riječ: $targetWord",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Pokušaj ponovo!",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onRestart,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE3D081))
            ) {
                Text(
                    text = "igraj ponovo !",
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedButton(onClick = onHome, border = BorderStroke(2.dp, Color(0xFFB33951))) {
                Text(text = "promijeni igru",
                    color = Color(0xFFE3D081),
                    fontSize = 16.sp
                )
            }
        }
    }
}