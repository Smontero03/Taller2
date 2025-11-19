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
import androidx.compose.material3.OutlinedTextField
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
fun RegisterScreen() {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Crear Cuenta", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(40.dp))
        OutlinedTextField(value="", onValueChange={}, label={ Text("Nombre") })
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(value="", onValueChange={}, label={ Text("Correo") })
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(value="", onValueChange={}, label={ Text("Contraseña") })
        Spacer(Modifier.height(24.dp))
        Button(onClick={}) { Text("Registrarse") }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    Taller2Theme {

    }
}