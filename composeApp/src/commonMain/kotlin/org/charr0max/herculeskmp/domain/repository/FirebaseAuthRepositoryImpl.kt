package org.charr0max.herculeskmp.domain.repository

import firebase.AuthResponse
import firebase.FirebaseAuth
import firebase.TokenResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import org.charr0max.herculeskmp.data.firebase.Firebase
import org.charr0max.herculeskmp.data.firebase.FirebaseDatabase
import org.charr0max.herculeskmp.data.firebase.FirebaseUser
import org.charr0max.herculeskmp.data.firebase.onCompletion

class FirebaseAuthRepositoryImpl(val firebase: Firebase) : FirebaseAuthRepository {
    private val firebaseAuth = FirebaseAuth()

    override fun signUpWithEmailAndPassword(email: String, password: String): Flow<AuthResponse> =
        callbackFlow {
            firebaseAuth.login(email,password,object : onCompletion<AuthResponse> {
                override fun onSuccess(authResponse: AuthResponse) {
                    // TODO: store user details in local db
                    updateUserInDatabase(authResponse.localId,authResponse.email,authResponse.refreshToken)
                    trySend(authResponse)
                }
                override fun onError(e: Exception) {
                    println(e.message)
                }
            })
        }

    private fun updateUserInDatabase(uid:String, email:String, token:String) {
        firebase.setCurrentUser(FirebaseUser(email, token, uid))
        val firebaseDatabase = FirebaseDatabase()
        GlobalScope.launch {
            val child = listOf("users",uid)
            val map = HashMap<String,Any>()
            map["uid"] = uid
            map["email"] = email
            firebaseDatabase.updateFirebaseDatabase(child,map,
                object : onCompletion<String> {
                    override fun onSuccess(T: String) {
                        print(T)
                    }

                    override fun onError(e: Exception) {
                        print(e.message)
                    }
                }
            )
        }
    }

    override fun login(email: String, password: String): Flow<AuthResponse> = callbackFlow {

    }

    override fun getRefreshToken(refreshToken: String?): Flow<TokenResponse> = callbackFlow {

    }
}