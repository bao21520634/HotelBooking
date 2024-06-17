package com.example.hotelbooking.data.remote

import com.example.hotelbooking.data.remote.dto.LoginRequest
import com.example.hotelbooking.data.remote.dto.RegisterRequest
import com.example.hotelbooking.domain.model.Message
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<Message>

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<Message>

    @POST("auth/logout")
    suspend fun logout(): Response<Message>
}
