package com.example.data.repos.user

import com.example.domain.models.AppUser
import com.example.domain.repos.UserOnlineDataSource
import com.example.domain.repos.UserRepository
import com.google.firebase.auth.AuthResult
import com.google.firebase.firestore.DocumentSnapshot
import javax.inject.Inject

class userRepositoryImpl
@Inject constructor(
    val userOnlineDataSource: UserOnlineDataSource
) : UserRepository {
    override suspend fun getUserFromFireBase(uId: String): DocumentSnapshot {
        try {
            return userOnlineDataSource.getUserFromFireBase(uId)

        } catch (ex: Exception) {
            throw ex
        }
    }

    override suspend fun signInFireBase(email: String, password: String): AuthResult {
        try {
            return userOnlineDataSource.signInFireBase(email, password)

        } catch (ex: Exception) {
            throw ex
        }

    }

    override suspend fun createAccountInFireBase(email: String, password: String): AuthResult {
        try {
            return userOnlineDataSource.createAccountInFireBase(email, password)

        } catch (ex: Exception) {
            throw ex
        }

    }

    override suspend fun logInFireBase(user: AppUser) {
        try {
            userOnlineDataSource.logInFireBase(user)

        } catch (ex: Exception) {
            throw ex
        }


    }
}