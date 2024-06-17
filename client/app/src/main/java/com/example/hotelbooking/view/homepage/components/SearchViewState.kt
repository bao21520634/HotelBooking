package com.example.hotelbooking.view.homepage.components

import com.example.hotelbooking.data.remote.dto.Place
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class SearchViewState (
    val location: String = "Thủ Đức, TP Hồ Chí Minh",
    val location_id: String = "XVirQKdxXNhsiY1Vlndt7ViwSCWmcL_VR7NMUqJBjpN3j2Y0lTlyN1yiyvWyQXa_6DTegKJRcjplvoj8Op3HjkHSPRFSvcpXBdbFHU6dhkfh0i1QHkQeBkXajXCyRBurR",
    val childCount: Int = 0,
    val adultCount: Int = 1,
    val roomCount: Int = 1,
    val checkIn: String = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date()),
    val checkOut: String = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date()),
    val sortOption: String = "",
    val page: String = "1"
)

data class PlaceViewState (
    val isLoading: Boolean = false,
    val predictionPlaces: List<Place> = emptyList(),
    val error: String? = null
)