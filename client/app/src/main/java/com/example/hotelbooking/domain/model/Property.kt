package com.example.hotelbooking.domain.model

data class Property(
    val name: String,
    val location: String,
    val description: String,
    val interior: List<String> = emptyList(),
    val facilities: List<String> = emptyList(),
    val pricePerNightWeekdays: Int = 0,
    val pricePerNightWeekends: Int = 0,
    val quantity: Int = 0,
    val imageUrls: List<String> = emptyList(),
    val bedrooms: List<Bedroom> = emptyList(),
)
