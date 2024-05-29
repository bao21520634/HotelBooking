package com.example.hotelbooking.data.remote

import com.example.hotelbooking.data.remote.dto.HotelsResponse
import com.example.hotelbooking.domain.model.Hotel
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface HotelsApi {
    @GET("hotels/top-bookings")
    suspend fun getTopBookings(
        @Query("page") page: Int
    ): HotelsResponse

    @GET("my-hotels/user/favorites")
    suspend fun getMyFavorites(
        @Header("Cookie") cookies: String,
        @Query("page") page: Int
    ): HotelsResponse

    @GET("my-hotels/user/history")
    suspend fun getMyHistory(
        @Header("Cookie") cookies: String,
        @Query("page") page: Int
    ): HotelsResponse

    @GET("my-bookings")
    suspend fun getMyBookings(
        @Header("Cookie") cookies: String,
        @Query("page") page: Int
    ): HotelsResponse

    @GET("hotels/{id}")
    suspend fun getHotel(
        @Header("Cookie") cookies: String,
        @Path("id") id: String
    ): Hotel
}