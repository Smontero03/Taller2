package com.example.taller2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taller2.ui.theme.Taller2Theme

@Composable
fun WaitingRoomScreen(
    viewModel: GameViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    roomId: String,
    onStartGame: () -> Unit
) {
    val players by viewModel.players.observeAsState(initial = emptyList())

    DisposableEffect(roomId) {
        viewModel.listenPlayers(roomId)
        onDispose { }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Sala de Espera", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Text("ID: $roomId", fontSize = 16.sp)
        Spacer(Modifier.height(20.dp))
        Text("Jugadores conectados:")
        Spacer(Modifier.height(20.dp))
        players.forEach { player ->
            Text(player.name, fontSize = 20.sp)
            Spacer(Modifier.height(8.dp))
        }
        Spacer(Modifier.height(30.dp))
        Button(onClick = {
            viewModel.startGame(roomId)
            onStartGame()
        }) { Text("Iniciar Partida") }
    }
}

@Preview(showBackground = true)
@Composable
fun WaitingRoomScreenPreview() {
    Taller2Theme {
        WaitingRoomScreen(roomId = "test_room", onStartGame = {}, viewModel = GameViewModel())
    }
}
