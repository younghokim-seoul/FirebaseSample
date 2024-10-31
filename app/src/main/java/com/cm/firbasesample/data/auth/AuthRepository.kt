package com.cm.firbasesample.data.auth

import com.cm.firbasesample.data.auth.model.User
import com.google.firebase.auth.AuthResult

interface AuthRepository {
    suspend fun getFirebaseUserUid(): String
    suspend fun signUpWithEmailAndPassword(user: User, password: String)
    suspend fun signInWithEmailAndPassword(email: String, password: String): AuthResult
    suspend fun getCurrentUser(): User
    suspend fun isCurrentUserExist() : Boolean
    suspend fun signOut()
}