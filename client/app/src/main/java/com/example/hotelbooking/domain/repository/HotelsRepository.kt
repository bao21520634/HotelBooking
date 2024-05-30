package com.example.hotelbooking.domain.repository

import arrow.core.Either
import com.example.hotelbooking.data.remote.dto.HotelsResponse
import com.example.hotelbooking.data.remote.dto.Place
import com.example.hotelbooking.domain.model.Hotel
import com.example.hotelbooking.domain.model.NetworkError

interface HotelsRepository {
    suspend fun getTopBookings(page: Int): Either<NetworkError, HotelsResponse>

    suspend fun getMyFavorites(page: Int): Either<NetworkError, HotelsResponse>

    suspend fun getMyHistory(page: Int): Either<NetworkError, HotelsResponse>

    suspend fun getMyBookings(page: Int): Either<NetworkError, HotelsResponse>

    suspend fun getHotel(id: String): Either<NetworkError, Hotel>

    suspend fun getLocationPredictions(destination: String): Either<NetworkError, List<Place>>

    suspend fun search(searchParams: Map<String, String>): Either<NetworkError, HotelsResponse>

    suspend fun getNearHotels(lng: String, lat: String):Either<NetworkError, HotelsResponse>
}