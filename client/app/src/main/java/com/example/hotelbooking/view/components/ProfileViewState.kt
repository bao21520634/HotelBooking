package com.example.hotelbooking.view.components

import com.example.hotelbooking.domain.model.Hotel
import com.example.hotelbooking.domain.model.User

data class ProfileViewState(
    var isLoading: Boolean = false,
    var user: User = User(),
    var error: String? = null
)
