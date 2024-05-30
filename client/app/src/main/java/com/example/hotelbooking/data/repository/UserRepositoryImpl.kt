package com.example.hotelbooking.data.repository

import android.content.SharedPreferences
import arrow.core.Either
import com.example.hotelbooking.data.mapper.toNetworkError
import com.example.hotelbooking.data.remote.UserApi
import com.example.hotelbooking.domain.model.NetworkError
import com.example.hotelbooking.domain.model.User
import com.example.hotelbooking.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi,
    private val sharedPreferences: SharedPreferences
): UserRepository {
    override suspend fun getUser(): Either<NetworkError, User> {
        return Either.catch {
            val jwt = sharedPreferences.getString("auth_token", null)

            if (jwt != null) {
                userApi.getUser(cookies = "auth_token=$jwt")
            } else {
                throw Exception("JWT not found")
            }
        }.mapLeft { it.toNetworkError() }
    }
}