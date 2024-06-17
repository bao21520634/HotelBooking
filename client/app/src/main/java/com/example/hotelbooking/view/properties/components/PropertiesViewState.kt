package com.example.hotelbooking.view.properties.components

data class PropertiesViewState(
    val name: String = "",
    val quantity: Int = 0,
    val address: String = "",
    val description: String = "",
    val type: String = "",
    val adultCount: Int = 0,
    val childCount: Int = 0,
    val facilities: List<String> = emptyList(),
    val bedrooms: List<Bedroom> = emptyList(),
    val interior: List<String> = emptyList(),
    val pricePerNightWeekdays: Int = 0,
    val pricePerNightWeekends: Int = 0,
    val starRating: Int = 4,
    val imageUrls: List<String> = emptyList(),
) {
    data class Bedroom(
        val type: String = "",
        val quantity: Int = 0,
    )
}