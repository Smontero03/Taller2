package com.example.taller2.viewmodel

import androidx.lifecycle.ViewModel
import com.example.taller2.model.ChatMessage
import com.example.taller2.model.GameStatus
import com.example.taller2.model.GameState
import com.example.taller2.model.Player
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameViewModel : ViewModel() {

    private val database = FirebaseDatabase.getInstance().reference
    private val _gameState = MutableStateFlow(GameState())
    val gameState: StateFlow<GameState> = _gameState.asStateFlow()
    private val _playersInRoom = MutableStateFlow<List<String>>(emptyList())
    val playersInRoom: StateFlow<List<String>> = _playersInRoom.asStateFlow()
    private val _roomCreatorId = MutableStateFlow<String?>(null)
    val roomCreatorId: StateFlow<String?> = _roomCreatorId.asStateFlow()
    private var playersListener: ValueEventListener? = null
    private var gameStateListener: ValueEventListener? = null

    private val emojis = listOf("🐶", "🐱", "🐭", "🐹", "🐰", "🦊", "🐻", "🐼", "🐨", "🐯", "🦁", "🐮", "🐷", "🐸", "🐵")

    fun createRoom(creatorId: String, creatorName: String, onRoomCreated: (String) -> Unit) {
        val roomId = (100000..999999).random().toString()
        database.child("rooms").child(roomId).child("creator").setValue(creatorId)
        database.child("rooms").child(roomId).child("players").child(creatorId).setValue(creatorName)
            .addOnSuccessListener {
                onRoomCreated(roomId)
            }
    }

    fun joinRoom(roomId: String, userId: String, userName: String, onJoinSuccess: () -> Unit, onJoinError: (String) -> Unit) {
        database.child("rooms").child(roomId).get().addOnSuccessListener {
            if (it.exists()) {
                database.child("rooms").child(roomId).child("players").child(userId).setValue(userName)
                    .addOnSuccessListener {
                        onJoinSuccess()
                    }
            } else {
                onJoinError("La sala no existe")
            }
        }.addOnFailureListener{
            onJoinError("Error al unirse a la sala")
        }
    }

    fun listenForPlayers(roomId: String) {
        val roomRef = database.child("rooms").child(roomId)

        playersListener = roomRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val creatorId = snapshot.child("creator").getValue(String::class.java)
                _roomCreatorId.value = creatorId

                val players = snapshot.child("players").children.mapNotNull {
                    it.getValue(String::class.java)
                }
                _playersInRoom.value = players
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    fun stopListeningForPlayers(roomId: String) {
        playersListener?.let {
            database.child("rooms").child(roomId).removeEventListener(it)
        }
    }

    fun startGame(roomId: String, players: List<String>) {
        val shuffledEmojis = emojis.shuffled()
        val gamePlayers = players.mapIndexed { index, name ->
            Player(uid = "", name = name, secretEmoji = shuffledEmojis[index])
        }
        val initialGameState = GameState(
            players = gamePlayers,
            gameStatus = GameStatus.IN_PROGRESS,
            currentPlayerTurn = gamePlayers.first().uid
        )
        database.child("games").child(roomId).setValue(initialGameState)
    }

    fun listenForGameState(roomId: String) {
        gameStateListener = database.child("games").child(roomId).addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.getValue(GameState::class.java)?.let { _gameState.value = it }
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    fun stopListeningForGameState(roomId: String) {
        gameStateListener?.let {
            database.child("games").child(roomId).removeEventListener(it)
        }
    }

    fun makeGuess(roomId: String, player: Player, guessedEmoji: String) {
        val newPlayers = _gameState.value.players.map {
            if (it.uid == player.uid) {
                it.copy(isAlive = it.secretEmoji == guessedEmoji)
            } else {
                it
            }
        }
        database.child("games").child(roomId).child("players").setValue(newPlayers)
    }

    fun sendMessage(roomId: String, message: ChatMessage) {
        database.child("games").child(roomId).child("chatMessages").push().setValue(message)
    }

    fun leaveGame(roomId: String, userId: String) {
        database.child("rooms").child(roomId).child("players").child(userId).removeValue()
    }
}