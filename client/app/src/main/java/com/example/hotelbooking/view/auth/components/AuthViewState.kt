package com.example.hotelbooking.view.auth.components

import com.example.hotelbooking.domain.model.Message

data class AuthViewState(
    val isLoading: Boolean = false,
    val message: Message? = null,
    val error: String? = null
)
