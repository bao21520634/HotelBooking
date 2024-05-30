package com.example.hotelbooking.data.remote

import com.example.hotelbooking.domain.model.User
import retrofit2.http.GET
import retrofit2.http.Header

interface UserApi {
    @GET("users/me")
    suspend fun getUser(@Header("Cookie") cookies: String): User
}