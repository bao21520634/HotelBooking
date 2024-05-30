package com.example.hotelbooking.view.components

import com.example.hotelbooking.domain.model.Hotel

data class HotelsViewState (
    val isLoading: Boolean = false,
    val hotels: List<Hotel> = emptyList(),
    val page: Int = 1,
    val error: String? = null
)