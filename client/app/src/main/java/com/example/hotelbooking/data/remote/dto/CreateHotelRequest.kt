package com.example.hotelbooking.data.remote.dto

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import retrofit2.http.Part

data class CreateHotelRequest(
    @Part val name: String,
    @Part val quantity: Int,
    @Part val address: String,
    @Part val description: String,
    @Part val type: String = "1",
    @Part val adultCount: Int = 0,
    @Part val childCount: Int = 0,
    @Part val facilities: List<String> = emptyList(),
    @Part val bedrooms: List<Bedroom> = emptyList(),
    @Part val interior: List<String> = emptyList(),
    @Part val pricePerNightWeekdays: Int = 0,
    @Part val pricePerNightWeekends: Int = 0,
    @Part val imageFiles: List<MultipartBody.Part>
)

data class Bedroom(
    @Part val type: String,
    @Part val quantity: Int
)