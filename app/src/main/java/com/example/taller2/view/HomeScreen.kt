package com.example.taller2.view

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taller2.viewmodel.AuthViewModel
import com.example.taller2.viewmodel.GameViewModel

@Composable
fun HomeScreen(
    onRoomNavigation: (String) -> Unit,
    authViewModel: AuthViewModel = viewModel(),
    gameViewModel: GameViewModel = viewModel()
) {
    val currentUser by authViewModel.currentUser.collectAsState()
    val currentUserId by remember { mutableStateOf(authViewModel.currentUserId) }
    var roomCode by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Bienvenido, ${currentUser?.displayName ?: "Usuario"}", fontSize = 26.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(40.dp))
        Button(onClick = {
            currentUserId?.let { gameViewModel.createRoom(it, onRoomNavigation) }
        }) { Text("Crear Sala") }
        Spacer(Modifier.height(20.dp))
        OutlinedTextField(value = roomCode, onValueChange = { roomCode = it }, label = { Text("Código de Sala") })
        Spacer(Modifier.height(10.dp))
        Button(onClick = {
            gameViewModel.joinRoom(roomCode, { onRoomNavigation(roomCode) }, {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            })
        }) { Text("Unirse a Sala") }
        Spacer(Modifier.height(40.dp))
        Button(onClick = { authViewModel.logout() }) { Text("Cerrar Sesión") }
    }
}