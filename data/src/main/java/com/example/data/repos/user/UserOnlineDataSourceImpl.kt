package com.example.data.repos.user

import com.example.domain.models.AppUser
import com.example.domain.repos.UserOnlineDataSource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class UserOnlineDataSourceImpl : UserOnlineDataSource {
    private val auth = Firebase.auth
    private val fs = Firebase.firestore

    override suspend fun signInFireBase(email: String, password: String): AuthResult {
        try {
            return auth.signInWithEmailAndPassword(email, password)
                .await()
        } catch (ex: Exception) {
            throw ex
        }
    }

    override suspend fun createAccountInFireBase(email: String, password: String): AuthResult {
        try {
            return auth.createUserWithEmailAndPassword(email, password)
                .await()

        } catch (ex: Exception) {
            throw ex
        }
    }

    override suspend fun getUserFromFireBase(uId: String): DocumentSnapshot {
        try {
            return fs.collection(AppUser.COLLECTION_NAME)
                .document(uId)
                .get()
                .await()

        } catch (ex: Exception) {
            throw ex
        }
    }

    override suspend fun logInFireBase(user: AppUser) {
        try {
            fs.collection(AppUser.COLLECTION_NAME)
                .document(user.id!!)
                .set(user)
                .await()

        } catch (ex: Exception) {
            throw ex
        }
    }

}