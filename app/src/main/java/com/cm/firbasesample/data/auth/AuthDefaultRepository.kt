package com.cm.firbasesample.data.auth

import com.cm.firbasesample.data.KeyChain.COLLECTION_USER
import com.cm.firbasesample.data.KeyChain.E_MAIL
import com.cm.firbasesample.data.KeyChain.ID
import com.cm.firbasesample.data.KeyChain.NICKNAME
import com.cm.firbasesample.data.KeyChain.PHONE_NUMBER
import com.cm.firbasesample.data.auth.model.User
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthDefaultRepository @Inject constructor(
    private val fireAuth: FirebaseAuth,
    private val fireStore: FirebaseFirestore
) : AuthRepository {
    override suspend fun getFirebaseUserUid(): String {
        return fireAuth.currentUser?.uid.orEmpty()
    }

    override suspend fun signUpWithEmailAndPassword(user: User, password: String) {
        fireAuth.createUserWithEmailAndPassword(user.email, password).await()

        val userModel = hashMapOf(
            ID to getFirebaseUserUid(),
            E_MAIL to user.email,
            NICKNAME to user.nickname,
            PHONE_NUMBER to user.phoneNumber
        )

        fireStore.collection(COLLECTION_USER).document(getFirebaseUserUid())
            .set(userModel).await()
    }

    override suspend fun signInWithEmailAndPassword(email: String, password: String): AuthResult {
        return fireAuth.signInWithEmailAndPassword(email, password).await()
    }

    override suspend fun getCurrentUser(): User {
        val user = fireStore.collection(COLLECTION_USER).document(getFirebaseUserUid()).get().await()

        return User(
            user[E_MAIL] as String,
            user[NICKNAME] as String,
            user[PHONE_NUMBER] as String,
        )
    }

    override suspend fun isCurrentUserExist(): Boolean {
        return fireAuth.currentUser != null
    }

    override suspend fun signOut() {
        return fireAuth.signOut()
    }
}