package com.example.taller2.model

enum class GameStatus {
    WAITING,
    IN_PROGRESS,
    FINISHED
}

data class ChatMessage(
    val sender: String = "",
    val message: String = ""
)

data class GameState(
    val players: List<Player> = emptyList(),
    val currentPlayerTurn: String = "", // UID of the current player
    val timer: Int = 30,
    val roundNumber: Int = 1,
    val gameStatus: GameStatus = GameStatus.WAITING,
    val winner: String? = null,
    val chatMessages: List<ChatMessage> = emptyList()
)