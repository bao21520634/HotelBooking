package com.example.hotelbooking.data.repository

import android.content.SharedPreferences
import arrow.core.Either
import com.example.hotelbooking.data.mapper.toNetworkError
import com.example.hotelbooking.data.remote.HotelsApi
import com.example.hotelbooking.data.remote.dto.HotelsResponse
import com.example.hotelbooking.domain.model.Hotel
import com.example.hotelbooking.domain.model.NetworkError
import com.example.hotelbooking.domain.repository.HotelsRepository
import javax.inject.Inject

class HotelsRepositoryImpl @Inject constructor(
    private val hotelsApi: HotelsApi,
    private val sharedPreferences: SharedPreferences
): HotelsRepository {
    override suspend fun getTopBookings(page: Int): Either<NetworkError, HotelsResponse> {
        return Either.catch {
            hotelsApi.getTopBookings(page)
        }.mapLeft { it.toNetworkError() }
    }

    override suspend fun getMyFavorites(page: Int): Either<NetworkError, HotelsResponse> {
        return Either.catch {
            val jwt = sharedPreferences.getString("auth_token", null)

            if (jwt != null) {
                hotelsApi.getMyFavorites(cookies = "auth_token=$jwt", page = page)
            } else {
                throw Exception("JWT not found")
            }
        }.mapLeft { it.toNetworkError() }
    }

    override suspend fun getMyHistory(page: Int): Either<NetworkError, HotelsResponse> {
        return Either.catch {
            val jwt = sharedPreferences.getString("auth_token", null)

            if (jwt != null) {
                hotelsApi.getMyHistory(cookies = "auth_token=$jwt", page = page)
            } else {
                throw Exception("JWT not found")
            }
        }.mapLeft { it.toNetworkError() }
    }

    override suspend fun getMyBookings(page: Int): Either<NetworkError, HotelsResponse> {
        return Either.catch {
            val jwt = sharedPreferences.getString("auth_token", null)

            if (jwt != null) {
                hotelsApi.getMyBookings(cookies = "auth_token=$jwt", page = page)
            } else {
                throw Exception("JWT not found")
            }
        }.mapLeft { it.toNetworkError() }
    }

    override suspend fun getHotel(id: String): Either<NetworkError, Hotel> {
        return Either.catch {
            val jwt = sharedPreferences.getString("auth_token", null)

            if (jwt != null) {
                hotelsApi.getHotel(cookies = "auth_token=$jwt", id = id)
            } else {
                throw Exception("JWT not found")
            }
        }.mapLeft { it.toNetworkError() }
    }
}