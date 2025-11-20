package com.example.taller2.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.userProfileChangeRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val _currentUser = MutableStateFlow<FirebaseUser?>(auth.currentUser)
    val currentUser = _currentUser.asStateFlow()
    val currentUserId: String?
        get() = auth.currentUser?.uid

    init {
        auth.addAuthStateListener { firebaseAuth ->
            _currentUser.value = firebaseAuth.currentUser
        }
    }

    fun register(
        username: String,
        email: String,
        pass: String,
        onRegisterSuccess: () -> Unit,
        onRegisterError: (String) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val user = auth.currentUser
                    val profileUpdates = userProfileChangeRequest {
                        displayName = username
                    }
                    user!!.updateProfile(profileUpdates)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                onRegisterSuccess()
                            }
                        }
                } else {
                    onRegisterError(it.exception?.message ?: "Error desconocido")
                }
            }
    }

    fun login(
        email: String,
        pass: String,
        onLoginSuccess: () -> Unit,
        onLoginError: (String) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    onLoginSuccess()
                } else {
                    onLoginError(it.exception?.message ?: "Error desconocido")
                }
            }
    }

    fun logout() {
        auth.signOut()
    }
}