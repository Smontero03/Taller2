package com.example.taller2.Data


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FirebaseRepository {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseDatabase.getInstance().reference

    fun login(email: String, pass: String, onResult: (Boolean) -> Unit) {
        auth.signInWithEmailAndPassword(email, pass)
            .addOnSuccessListener {
                onResult(true)
            }
            .addOnFailureListener {
                onResult(false)
            }


    }
    fun register(name: String, email: String, pass: String, onResult: (Boolean) -> Unit) {

        auth.createUserWithEmailAndPassword(email, pass)
            .addOnSuccessListener {
                val uid = auth.currentUser!!.uid

                val userData = mapOf(
                    "name" to name,
                    "email" to email
                )

                db.child("users").child(uid).setValue(userData)
                onResult(true)
            }
            .addOnFailureListener {
                onResult(false)
            }

    }

    fun createRoom(playerId: String, callback: (String) -> Unit) { val roomId = db.child("rooms").push().key!!

        db.child("rooms/$roomId/players/$playerId").setValue(
            Player(playerId, "Jugador", "", false, false)
        )

        callback(roomId)}
    fun joinRoom(roomId: String, playerId: String, callback: (Boolean) -> Unit) {

        db.child("rooms/$roomId").get()
            .addOnSuccessListener {
                if (it.exists()) {
                    db.child("rooms/$roomId/players/$playerId")
                        .setValue(Player(playerId, "Jugador", "", false, false))
                    callback(true)
                } else callback(false)
            }

    }

    fun listenPlayers(roomId: String, callback: (List<Player>) -> Unit) {
        db.child("rooms/$roomId/players")
            .addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    val players = snapshot.children.mapNotNull {
                        it.getValue(Player::class.java)
                    }
                    callback(players)
                }

                override fun onCancelled(error: DatabaseError) {}
            })

    }
    fun sendMessage(roomId: String, msg: Message) {
        val msgId = db.child("rooms/$roomId/chat").push().key!!
        db.child("rooms/$roomId/chat/$msgId").setValue(msg)

    }
    fun listenChat(roomId: String, callback: (List<Message>) -> Unit) {
        db.child("rooms/$roomId/chat")
            .addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    val messages = snapshot.children.mapNotNull {
                        it.getValue(Message::class.java)
                    }
                    callback(messages)
                }

                override fun onCancelled(error: DatabaseError) {}
            })

    }

    fun assignEmojis(roomId: String) {
        val emojis = listOf("😀","🐶","🐱","🐸","🎃","🧁","🦄","🐵")

        db.child("rooms/$roomId/players").get()
            .addOnSuccessListener { snapshot ->

                snapshot.children.forEach { child ->
                    val playerId = child.key!!
                    val randomEmoji = emojis.random()

                    db.child("rooms/$roomId/players/$playerId/emoji")
                        .setValue(randomEmoji)
                }
            }

    }
    fun startTurn(roomId: String) {
        db.child("rooms/$roomId/players").get()
            .addOnSuccessListener { snapshot ->
                val alive = snapshot.children
                    .mapNotNull { it.getValue(Player::class.java) }
                    .filter { !it.eliminated }

                val next = alive.random()

                db.child("rooms/$roomId/game/currentTurn").setValue(next.id)
            }

    }
    fun eliminatePlayer(roomId: String, playerId: String) {
        db.child("rooms/$roomId/players/$playerId/eliminated")
            .setValue(true)

    }
}