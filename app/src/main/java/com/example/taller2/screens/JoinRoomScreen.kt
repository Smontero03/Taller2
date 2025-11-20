package com.example.taller2.screens

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taller2.ui.theme.Taller2Theme

@Composable
fun JoinRoomScreen(
    viewModel: GameViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    onJoinSuccess: (String) -> Unit
) {
    var roomId by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Unirse a Sala", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(30.dp))
        OutlinedTextField(
            value = roomId,
            onValueChange = { roomId = it },
            label = { Text("Código de Sala") }
        )
        Spacer(Modifier.height(20.dp))
        Button(onClick = {
            viewModel.joinRoom(roomId) { success ->
                if (success) {
                    onJoinSuccess(roomId)
                }
            }
        }) { Text("Entrar") }
    }
}

@Preview(showBackground = true)
@Composable
fun JoinRoomScreenPreview() {
    Taller2Theme {
        JoinRoomScreen(onJoinSuccess = {}, viewModel = GameViewModel())
    }
}
