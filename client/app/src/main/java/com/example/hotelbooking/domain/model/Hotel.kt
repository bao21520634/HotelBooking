package com.example.hotelbooking.domain.model

data class Hotel(
    val _id: String = "",
    val address: String = "",
    val bedrooms: List<Bedroom> = emptyList(),
    val bookings: List<Any> = emptyList(),
    val city: String = "",
    val country: String = "",
    val description: String = "",
    val facilities: List<String> = emptyList(),
    val adultCount: Int = 0,
    val childCount: Int = 0,
    val imageUrls: List<String> = emptyList(),
    val interior: List<String> = emptyList(),
    val lastUpdated: String = "",
    val length: Int = 0,
    val location: Location = Location(),
    val name: String = "",
    var totalPrice: Int = 0,
    val pricePerNightWeekdays: Int = 0,
    val pricePerNightWeekends: Int = 0,
    val quantity: Int = 0,
    val starRating: Int = 0,
    val type: String = "",
    val userId: String = ""
)

data class Bedroom(
    val _id: String = "",
    val quantity: Int = 0,
    val type: String = ""
)

data class Location(
    val coordinates: List<Double> = emptyList(),
    val type: String = ""
)