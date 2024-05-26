package com.example.hotelbooking.domain.repository

import arrow.core.Either
import com.example.hotelbooking.domain.model.Hotel
import com.example.hotelbooking.domain.model.NetworkError

interface HotelsRepository {
    suspend fun getTopBookings() : Either<NetworkError, List<Hotel>>

}