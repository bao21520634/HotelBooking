package com.example.hotelbooking.view.homepage.components

import com.example.hotelbooking.domain.model.Hotel

data class HotelsViewState (
    val isLoading: Boolean = false,
    val hotels: List<Hotel> = emptyList(),
    val error: String? = null
)