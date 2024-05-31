package com.example.hotelbooking.data.remote.dto

data class CheckoutRequest(
    val totalCost: Int,
    val checkIn: String,
    val checkOut: String,
    val adultCount: Int,
    val childCount: Int
)