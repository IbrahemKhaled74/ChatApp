package com.example.domain.repos

import com.example.domain.models.AppUser
import com.google.firebase.auth.AuthResult
import com.google.firebase.firestore.DocumentSnapshot

interface UserRepository {
    suspend fun createAccountInFireBase(email: String, password: String): AuthResult
    suspend fun logInFireBase(user: AppUser)
    suspend fun signInFireBase(email: String, password: String): AuthResult
    suspend fun getUserFromFireBase(uId: String): DocumentSnapshot
}

interface UserOnlineDataSource {
    suspend fun createAccountInFireBase(email: String, password: String): AuthResult
    suspend fun logInFireBase(user: AppUser)
    suspend fun signInFireBase(email: String, password: String): AuthResult
    suspend fun getUserFromFireBase(uId: String): DocumentSnapshot

}
