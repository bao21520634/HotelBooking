package com.example.hotelbooking.data.remote

import com.example.hotelbooking.domain.model.Hotel
import retrofit2.http.GET

interface HotelsApi {
    @GET("hotels")
    suspend fun getTopBookings(): List<Hotel>
}