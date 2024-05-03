package com.example.hotelbooking.ui.model

import androidx.annotation.DrawableRes

data class Hotel(
    @DrawableRes val hotelThumbnail: Int,
    val hotelName: String,
    val starRating: Float,

    val commentRating: Float,
    val numberOfComment: Int,
    val hotelAddress: String,
    val numberOfBed: Int,
    val hotelPrice: Int,
    val isFeatured: Boolean = false
)
