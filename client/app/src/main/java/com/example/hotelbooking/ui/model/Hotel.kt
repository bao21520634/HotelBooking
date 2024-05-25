package com.example.hotelbooking.ui.model

import androidx.annotation.DrawableRes
import com.example.hotelbooking.R

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
val sampleData = listOf(
    Hotel(
        hotelThumbnail = R.drawable.hotel_thumbnail,
        hotelName = "Sunset Paradise Resort",
        starRating = 4.5f,
        commentRating = 9.2f,
        numberOfComment = 350,
        hotelAddress = "123 Ocean View Blvd, Miami, FL",
        numberOfBed = 2,
        hotelPrice = 200,
        isFeatured = true
    ),
    Hotel(
        hotelThumbnail = R.drawable.hotel_thumbnail,
        hotelName = "Mountain View Lodge",
        starRating = 3.8f,
        commentRating = 8.0f,
        numberOfComment = 150,
        hotelAddress = "456 Summit Way, Aspen, CO",
        numberOfBed = 3,
        hotelPrice = 180,
        isFeatured = false
    ),
    Hotel(
        hotelThumbnail = R.drawable.hotel_thumbnail,
        hotelName = "Cityscape Suites",
        starRating = 4.2f,
        commentRating = 8.5f,
        numberOfComment = 220,
        hotelAddress = "789 Downtown Ave, New York, NY",
        numberOfBed = 1,
        hotelPrice = 250,
        isFeatured = true
    ),
    Hotel(
        hotelThumbnail = R.drawable.hotel_thumbnail,
        hotelName = "Tranquil Gardens Inn",
        starRating = 4.7f,
        commentRating = 9.5f,
        numberOfComment = 420,
        hotelAddress = "321 Serenity St, Kyoto, Japan",
        numberOfBed = 2,
        hotelPrice = 300,
        isFeatured = false
    ),
    Hotel(
        hotelThumbnail = R.drawable.hotel_thumbnail,
        hotelName = "Riverside Retreat",
        starRating = 4.0f,
        commentRating = 8.2f,
        numberOfComment = 180,
        hotelAddress = "567 Riverbank Rd, Paris, France",
        numberOfBed = 1,
        hotelPrice = 220,
        isFeatured = true
    ),
    Hotel(
        hotelThumbnail = R.drawable.hotel_thumbnail,
        hotelName = "Sunny Side Resort",
        starRating = 4.3f,
        commentRating = 8.8f,
        numberOfComment = 280,
        hotelAddress = "876 Sunshine Blvd, Sydney, Australia",
        numberOfBed = 3,
        hotelPrice = 280,
        isFeatured = false
    ),
    Hotel(
        hotelThumbnail = R.drawable.hotel_thumbnail,
        hotelName = "Forest Haven Lodge",
        starRating = 4.6f,
        commentRating = 9.3f,
        numberOfComment = 390,
        hotelAddress = "234 Wilderness Way, Vancouver, Canada",
        numberOfBed = 2,
        hotelPrice = 320,
        isFeatured = true
    ),
    Hotel(
        hotelThumbnail = R.drawable.hotel_thumbnail,
        hotelName = "Urban Oasis Hotel",
        starRating = 3.9f,
        commentRating = 8.1f,
        numberOfComment = 200,
        hotelAddress = "789 Metropolitan Ave, London, UK",
        numberOfBed = 1,
        hotelPrice = 230,
        isFeatured = false
    ),
    Hotel(
        hotelThumbnail = R.drawable.hotel_thumbnail,
        hotelName = "Seaside Serenity Resort",
        starRating = 4.4f,
        commentRating = 8.9f,
        numberOfComment = 310,
        hotelAddress = "543 Beachfront Blvd, Maldives",
        numberOfBed = 2,
        hotelPrice = 270,
        isFeatured = true
    ),
    Hotel(
        hotelThumbnail = R.drawable.hotel_thumbnail,
        hotelName = "Historic Mansion Hotel",
        starRating = 4.8f,
        commentRating = 9.7f,
        numberOfComment = 450,
        hotelAddress = "987 Heritage St, Rome, Italy",
        numberOfBed = 3,
        hotelPrice = 350,
        isFeatured = false
    )
)