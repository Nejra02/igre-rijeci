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
fun WordleDodajRijecPopup(
    show: Boolean,
    onDismiss: () -> Unit,
    onSubmit: (String) -> Unit
) {
    var novaRijec by remember { mutableStateOf("") }

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
                    text = "Predloži novu riječ !",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF54494B)
                )

                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "unesite riječ od 5 slova",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = novaRijec,
                    onValueChange = { novaRijec = it },
                    placeholder = { Text(":)") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Characters)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Button(onClick = {
                        novaRijec = ""
                        onDismiss()
                    },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE3D081))
                    ) {
                        Text(text = "Otkaži", fontSize = 16.sp)
                    }
                    val context = LocalContext.current

                    Button(
                        onClick = {
                            if (novaRijec.length == 5 && novaRijec.all { it.isLetter() }) {
                                onSubmit(novaRijec.uppercase())
                                novaRijec = ""
                                onDismiss()
                                Toast.makeText(context, "Riječ je dodana !", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(context, "Riječ nije u ispravnom formatu !", Toast.LENGTH_SHORT).show()
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE3D081))
                    ) {
                        Text(text = "Pošalji", fontSize = 16.sp)
                    }

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWordleDodajRijecPopup() {
    WordleDodajRijecPopup(
        show = true,
        onDismiss = {},
        onSubmit = { println("Dodana riječ: $it") }
    )
}

