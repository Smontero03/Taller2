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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taller2.ui.theme.Taller2Theme

@Composable
fun CreateRoomScreen(
    viewModel: GameViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    onRoomCreated: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Crear Sala", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(30.dp))
        Button(onClick = {
            viewModel.createRoom { roomId ->
                onRoomCreated(roomId)
            }
        }) { Text("Generar ID de Sala") }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateRoomScreenPreview() {
    Taller2Theme {
        CreateRoomScreen(onRoomCreated = {}, viewModel = GameViewModel())
    }
}
