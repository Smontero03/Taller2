package com.example.taller2.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taller2.ui.theme.Taller2Theme

@Composable
fun GameScreen(roomId: String) {
    Column(modifier = Modifier.fillMaxSize()) {


// --------- HEADER ---------
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Ronda 1", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text("Tiempo: 20", fontSize = 20.sp)
        }


// --------- EMOJIS DE LOS OTROS JUGADORES ---------
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Emojis de otros jugadores:", fontSize = 18.sp, fontWeight = FontWeight.Medium)
            Spacer(Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                Text("🐶", fontSize = 40.sp)
                Text("🐱", fontSize = 40.sp)
                Text("🐸", fontSize = 40.sp)
            }
        }


        Spacer(Modifier.height(20.dp))



        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Selecciona tu emoji:")
            Spacer(Modifier.height(10.dp))
            Button(onClick = {}) { Text("Adivinar Emoji") }
        }


        Spacer(Modifier.height(30.dp))


// --------- CHAT ---------
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text("Chat", fontSize = 18.sp, fontWeight = FontWeight.Medium)
            Spacer(Modifier.height(10.dp))


            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(Color.LightGray.copy(alpha = 0.2f))
            ) {

            }


            OutlinedTextField(value = "", onValueChange = {}, modifier = Modifier.fillMaxWidth(), label = { Text("Mensaje...") })
            Spacer(Modifier.height(8.dp))
            Button(onClick = {}, modifier = Modifier.align(Alignment.End)) { Text("Enviar") }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    Taller2Theme {
        GameScreen(roomId = "test_room")
    }
}
