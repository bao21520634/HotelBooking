package com.example.hotelbooking.data.remote

import com.example.hotelbooking.domain.model.User
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApi {
    @GET("users/me")
    suspend fun getUser(@Header("Cookie") cookies: String): User

    @POST("users/favorites/{hotelId}")
    suspend fun favorite(
        @Header("Cookie") cookies: String,
        @Path("hotelId") hotelId: String
    ): User
}