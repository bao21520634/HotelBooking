package com.example.hotelbooking.view.components

import com.example.hotelbooking.domain.model.Hotel
import com.example.hotelbooking.domain.model.User

data class ProfileViewState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val error: String? = null
)
