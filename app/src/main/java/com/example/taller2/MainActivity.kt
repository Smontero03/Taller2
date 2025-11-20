package com.example.taller2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
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
            onRegisterSuccess = { navController.navigate("login") },
            onBack = { navController.popBackStack() }
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
            GameScreen(
                roomId = roomId,
                onLeaveGame = {
                    navController.navigate("home") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            )
        }
    }
}
