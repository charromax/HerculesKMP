package org.charr0max.herculeskmp.domain.repository

import firebase.AuthResponse
import firebase.TokenResponse
import kotlinx.coroutines.flow.Flow

interface FirebaseAuthRepository {
    fun signUpWithEmailAndPassword(
        email: String, password: String
    ): Flow<AuthResponse>

    fun login(
        email: String, password: String
    ): Flow<AuthResponse>

    fun getRefreshToken(refreshToken: String?): Flow<TokenResponse>
}