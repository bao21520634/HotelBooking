package com.example.hotelbooking.view.components

import com.example.hotelbooking.domain.model.Hotel

data class HotelViewState (
    val isLoading: Boolean = false,
    val hotel: Hotel = Hotel(),
    val error: String? = null
)
