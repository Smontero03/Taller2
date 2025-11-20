package com.example.taller2.Data

data class Player(
    val id: String = "",
    val name: String = "",
    val emoji: String = "",
    val eliminated: Boolean = false,
    val isTurn: Boolean = false
)