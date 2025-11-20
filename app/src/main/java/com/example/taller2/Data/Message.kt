package com.example.taller2.Data

data class Message(
    val id: String = "",
    val playerId: String = "",
    val text: String = "",
    val timestamp: Long = System.currentTimeMillis()
)