package com.example.hotelbooking.domain.repository

import arrow.core.Either
import com.example.hotelbooking.domain.model.Message
import com.example.hotelbooking.domain.model.NetworkError
import retrofit2.Call
import retrofit2.Response

interface AuthRepository {
    suspend fun login(email: String, password: String): Either<NetworkError, Response<Message>>

    suspend fun register(email: String, username: String, password: String, confirmPassword: String): Either<NetworkError, Response<Message>>

    suspend fun logout(): Either<NetworkError, Response<Message>>
}