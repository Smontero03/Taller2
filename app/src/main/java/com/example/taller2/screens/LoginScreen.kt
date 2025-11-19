package com.example.taller2.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.taller2.screens.ui.theme.Taller2Theme



@Composable
fun LoginScreen() {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Emoji Guess", fontSize = 32.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(40.dp))
        OutlinedTextField(value="", onValueChange={}, label={ Text("Correo") })
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(value="", onValueChange={}, label={ Text("Contraseña") })
        Spacer(Modifier.height(24.dp))
        Button(onClick={}) { Text("Ingresar") }
        TextButton(onClick={}) { Text("Registrarse") }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Taller2Theme {
        Greeting("Android")
    }
}