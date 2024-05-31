package com.example.hotelbooking.view.components

import com.example.hotelbooking.domain.model.Hotel

data class CheckoutViewState (
    val isLoading: Boolean = false,
    var url: String = "",
    val error: String? = null
)