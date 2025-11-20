package com.example.taller2.view

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taller2.viewmodel.GameViewModel

@Composable
fun GameScreen(roomId: String, viewModel: GameViewModel = viewModel()) {
    val gameState by viewModel.gameState.collectAsState()
    var guess by remember { mutableStateOf("") }
    // For now, we assume the user is Player 1. This should be replaced with the actual user.
    val currentUser = gameState.players.firstOrNull()

    Column(modifier = Modifier.fillMaxSize()) {
        // --------- HEADER ---------
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Ronda 1", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text("Tiempo: ${gameState.timer}", fontSize = 20.sp)
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
                gameState.players.forEach { player ->
                    if (player.name != currentUser?.name) { // Show other players' emojis
                        Text(player.secretEmoji, fontSize = 40.sp)
                    }
                }
            }
        }

        Spacer(Modifier.height(20.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = guess,
                onValueChange = { guess = it },
                label = { Text("Adivina tu emoji") }
            )
            Spacer(Modifier.height(10.dp))
            Button(onClick = {
                currentUser?.let { player ->
                    viewModel.makeGuess(player, guess)
                    guess = ""
                }
            }) { Text("Adivinar Emoji") }
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
            ) {}

            OutlinedTextField(value = "", onValueChange = {}, modifier = Modifier.fillMaxWidth(), label = { Text("Mensaje...") })
            Spacer(Modifier.height(8.dp))
            Button(onClick = {}, modifier = Modifier.align(Alignment.End)) { Text("Enviar") }
        }
    }
}
