package com.example.taller2.view

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taller2.ui.theme.Taller2Theme
import com.example.taller2.viewmodel.AuthViewModel
import com.example.taller2.viewmodel.GameViewModel

@Composable
fun WaitingRoomScreen(
    roomId: String,
    onStartGame: () -> Unit,
    authViewModel: AuthViewModel = viewModel(),
    gameViewModel: GameViewModel = viewModel()
) {
    val playersInRoom by gameViewModel.playersInRoom.collectAsState()
    val roomCreatorId by gameViewModel.roomCreatorId.collectAsState()
    val currentUserId = authViewModel.currentUserId

    LaunchedEffect(roomId) {
        gameViewModel.listenForPlayers(roomId)
    }

    DisposableEffect(roomId) {
        onDispose {
            gameViewModel.stopListeningForPlayers(roomId)
        }
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
        playersInRoom.forEach {
            Text(it, fontSize = 20.sp)
            Spacer(Modifier.height(8.dp))
        }
        Spacer(Modifier.height(30.dp))
        if (currentUserId == roomCreatorId) {
            Button(onClick = onStartGame) { Text("Iniciar Partida") }
        } else {
            Text("Esperando a que el creador inicie la partida...", fontSize = 16.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WaitingRoomScreenPreview() {
    Taller2Theme {
        WaitingRoomScreen(roomId = "test_room", onStartGame = {})
    }
}
