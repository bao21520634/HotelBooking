package com.example.hotelbooking.data.repository

import arrow.core.Either
import com.example.hotelbooking.data.mapper.toNetworkError
import com.example.hotelbooking.data.remote.HotelsApi
import com.example.hotelbooking.domain.model.Hotel
import com.example.hotelbooking.domain.model.NetworkError
import com.example.hotelbooking.domain.repository.HotelsRepository
import kotlinx.coroutines.CancellationException
import javax.inject.Inject

class HotelsRepositoryImpl @Inject constructor(
    private val hotelsApi: HotelsApi
): HotelsRepository {
    override suspend fun getTopBookings(): Either<NetworkError, List<Hotel>> {
        return Either.catch {
            hotelsApi.getTopBookings()
        }.mapLeft { it.toNetworkError() }
    }

}