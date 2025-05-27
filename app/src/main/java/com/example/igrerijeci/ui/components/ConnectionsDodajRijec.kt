package com.example.igrerijeci.ui.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ConnectionsDodajGrupuPopup(
    show: Boolean,
    onDismiss: () -> Unit,
    onSubmit: (List<String>) -> Unit
) {
    val context = LocalContext.current
    var rijeci = remember { List(4) { mutableStateOf("") } }

    if (show) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.6f)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .background(Color(0xFF91C7B1), shape = RoundedCornerShape(16.dp))
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Predloži novu grupu riječi!",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF54494B)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Unesite 4 povezane riječi",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(16.dp))

                rijeci.forEachIndexed { index, state ->
                    OutlinedTextField(
                        value = state.value,
                        onValueChange = { rijeci[index].value = it },
                        label = { Text("Riječ ${index + 1}") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    )
                }


                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            rijeci = List(4) { mutableStateOf("") }
                            onDismiss()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE3D081))
                    ) {
                        Text("Otkaži", fontSize = 16.sp)
                    }

                    Button(
                        onClick = {
                            val unosi = rijeci.map { it.value.trim() }

                            if (unosi.all { it.isNotEmpty() && it.all { ch -> ch.isLetter() } }) {
                                onSubmit(unosi)
                                Toast.makeText(context, "Grupa je predložena!", Toast.LENGTH_SHORT).show()
                                rijeci = List(4) { mutableStateOf("") }
                                onDismiss()
                            } else {
                                Toast.makeText(context, "Sve riječi moraju biti ispravne!", Toast.LENGTH_SHORT).show()
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE3D081))
                    ) {
                        Text("Pošalji", fontSize = 16.sp)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewConnectionsDodajGrupuPopup() {
    ConnectionsDodajGrupuPopup(
        show = true,
        onDismiss = {},
        onSubmit = { println("Grupa: $it") }
    )
}
