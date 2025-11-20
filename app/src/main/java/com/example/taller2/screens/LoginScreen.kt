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
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taller2.ui.theme.Taller2Theme

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onGoToRegister: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Emoji Guess", fontSize = 32.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(40.dp))
        OutlinedTextField(value = "", onValueChange = {}, label = { Text("Correo") })
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(value = "", onValueChange = {}, label = { Text("Contraseña") })
        Spacer(Modifier.height(24.dp))
        Button(onClick = onLoginSuccess) { Text("Ingresar") }
        TextButton(onClick = onGoToRegister) { Text("Registrarse") }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    Taller2Theme {
        LoginScreen(onLoginSuccess = {}, onGoToRegister = {})
    }
}
