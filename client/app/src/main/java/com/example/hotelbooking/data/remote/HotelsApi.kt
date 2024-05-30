package com.example.hotelbooking.data.remote

import com.example.hotelbooking.data.remote.dto.HotelsResponse
import com.example.hotelbooking.data.remote.dto.Place
import com.example.hotelbooking.domain.model.Hotel
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

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

    @GET("hotels/auto-complete")
    suspend fun getLocationPredictions(
        @Query("destination") destination: String
    ): List<Place>

    @GET("hotels/search")
    suspend fun search(
        @Header("Cookie") cookies: String,
        @QueryMap searchParams: Map<String, String>
    ): HotelsResponse

    @GET("hotels/near-here")
    suspend fun getNearHotel(
        @Header("Cookie") cookies: String,
        @Query("lng") lng: String,
        @Query("lat") lat: String
    ): HotelsResponse
}