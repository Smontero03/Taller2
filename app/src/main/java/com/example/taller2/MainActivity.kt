package com.example.taller2

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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taller2.screens.CreateRoomScreen
import com.example.taller2.screens.GameScreen
import com.example.taller2.screens.HomeScreen
import com.example.taller2.screens.JoinRoomScreen
import com.example.taller2.screens.LoginScreen
import com.example.taller2.screens.RegisterScreen
import com.example.taller2.screens.WaitingRoomScreen
import com.example.taller2.ui.theme.Taller2Theme
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContent {
            AppNavigation()
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        composable("login") { LoginScreen(
            onLoginSuccess = { navController.navigate("home") },
            onGoToRegister = { navController.navigate("register") }
        )}

        composable("register") { RegisterScreen(
            onRegisterSuccess = { navController.navigate("login") }
        )}

        composable("home") { HomeScreen(
            onCreateRoom = { navController.navigate("create_room") },
            onJoinRoom = { navController.navigate("join_room") }
        )}

        composable("create_room") { CreateRoomScreen(
            onRoomCreated = { roomId ->
                navController.navigate("waiting_room/$roomId")
            }
        )}

        composable("join_room") { JoinRoomScreen(
            onJoinSuccess = { roomId ->
                navController.navigate("waiting_room/$roomId")
            }
        )}

        composable("waiting_room/{roomId}") { backStackEntry ->
            val roomId = backStackEntry.arguments?.getString("roomId")!!
            WaitingRoomScreen(
                roomId = roomId,
                onStartGame = {
                    navController.navigate("game/$roomId")
                }
            )
        }

        composable("game/{roomId}") { backStackEntry ->
            val roomId = backStackEntry.arguments?.getString("roomId")!!
            GameScreen(roomId = roomId)
        }
    }
}
