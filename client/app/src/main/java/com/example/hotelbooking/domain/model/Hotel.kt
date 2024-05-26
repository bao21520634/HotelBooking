package com.example.hotelbooking.domain.model

data class Hotel(
    val _id: String,
    val address: String,
    val bedrooms: List<Bedroom>,
    val bookings: List<Any>,
    val city: String,
    val country: String,
    val description: String,
    val facilities: List<String>,
    val guestCount: Int,
    val imageUrls: List<String>,
    val interior: List<String>,
    val lastUpdated: String,
    val length: Int,
    val location: Location,
    val name: String,
    val pricePerNightWeekdays: Int,
    val pricePerNightWeekends: Int,
    val quantity: Int,
    val starRating: Int,
    val type: String,
    val userId: String
)

data class Bedroom(
    val _id: String,
    val quantity: Int,
    val type: String
)

data class Location(
    val coordinates: List<Double>,
    val type: String
)