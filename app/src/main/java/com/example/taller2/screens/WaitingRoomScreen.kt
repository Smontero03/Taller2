package com.example.taller2.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taller2.screens.ui.theme.Taller2Theme


@Composable
fun WaitingRoomScreen() {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Sala de Espera", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(20.dp))
        Text("Jugadores conectados:")
        Spacer(Modifier.height(20.dp))
// Aquí se cargaría la lista real de jugadores
        repeat(4) {
            Text("Jugador ${(it+1)}", fontSize = 20.sp)
            Spacer(Modifier.height(8.dp))
        }
        Spacer(Modifier.height(30.dp))
        Button(onClick={}) { Text("Iniciar Partida") }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    Taller2Theme {

    }
}