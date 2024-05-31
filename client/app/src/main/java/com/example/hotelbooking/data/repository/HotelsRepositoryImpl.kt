package com.example.hotelbooking.data.repository

import android.content.SharedPreferences
import arrow.core.Either
import com.example.hotelbooking.data.mapper.toNetworkError
import com.example.hotelbooking.data.remote.HotelsApi
import com.example.hotelbooking.data.remote.dto.CheckoutRequest
import com.example.hotelbooking.data.remote.dto.CheckoutResponse
import com.example.hotelbooking.data.remote.dto.HotelsResponse
import com.example.hotelbooking.data.remote.dto.Place
import com.example.hotelbooking.domain.model.Hotel
import com.example.hotelbooking.domain.model.NetworkError
import com.example.hotelbooking.domain.repository.HotelsRepository
import javax.inject.Inject

class HotelsRepositoryImpl @Inject constructor(
    private val hotelsApi: HotelsApi,
    private val sharedPreferences: SharedPreferences
) : HotelsRepository {
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

    override suspend fun getLocationPredictions(destination: String): Either<NetworkError, List<Place>> {
        return Either.catch {
            hotelsApi.getLocationPredictions(destination)
        }.mapLeft { it.toNetworkError() }
    }

    override suspend fun search(searchParams: Map<String, String>): Either<NetworkError, HotelsResponse> {
        return Either.catch {
            val jwt = sharedPreferences.getString("auth_token", null)

            if (jwt != null) {
                hotelsApi.search(
                    cookies = "auth_token=$jwt",
                    searchParams
                )
            } else {
                throw Exception("JWT not found")
            }
        }.mapLeft { it.toNetworkError() }
    }

    override suspend fun getNearHotels(
        lng: String,
        lat: String
    ): Either<NetworkError, HotelsResponse> {
        return Either.catch {
            val jwt = sharedPreferences.getString("auth_token", null)

            if (jwt != null) {
                hotelsApi.getNearHotel(cookies = "auth_token=$jwt", lng = lng, lat = lat)

            } else {
                throw Exception("JWT not found")
            }
        }.mapLeft { it.toNetworkError() }
    }

    override suspend fun checkout(
        hotelId: String,
        totalCost: Int,
        checkIn: String,
        checkOut: String,
        adultCount: Int,
        childCount: Int
    ): Either<NetworkError, CheckoutResponse> {
        return Either.catch {
            val jwt = sharedPreferences.getString("auth_token", null)

            if (jwt != null) {
                hotelsApi.checkout(
                    cookies = "auth_token=$jwt",
                    hotelId = hotelId,
                    body = CheckoutRequest(totalCost, checkIn, checkOut, adultCount, childCount)
                )

            } else {
                throw Exception("JWT not found")
            }
        }.mapLeft { it.toNetworkError() }
    }
}