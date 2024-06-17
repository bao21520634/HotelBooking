package com.example.hotelbooking.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.example.hotelbooking.data.remote.AuthApi
import com.example.hotelbooking.data.remote.dto.LoginRequest
import com.example.hotelbooking.domain.model.NetworkError
import com.example.hotelbooking.domain.repository.AuthRepository
import arrow.core.Either
import com.example.hotelbooking.data.mapper.toNetworkError
import com.example.hotelbooking.data.remote.dto.RegisterRequest
import com.example.hotelbooking.domain.model.Message
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val authApi: AuthApi
): AuthRepository {
    override suspend fun login(email: String, password: String): Either<NetworkError, Response<Message>> {
        val request = LoginRequest(email, password)
        return Either.catch {
            val call = authApi.login(request)
            if (call.isSuccessful) {
                val cookies = call.headers().values("Set-Cookie")
                saveCookies(cookies)
                call
            } else {
                throw Exception("Login failed")
            }
        }.mapLeft { it.toNetworkError() }
    }
    override suspend fun register(email: String, username: String, password: String, confirmPassword: String): Either<NetworkError, Response<Message>> {
        val request = RegisterRequest(email, username, password, confirmPassword)
        return Either.catch {
            val call = authApi.register(request)
            if (call.isSuccessful) {
                val cookies = call.headers().values("Set-Cookie")
                saveCookies(cookies)
                call
            } else {
                throw Exception("Registration failed")
            }
        }.mapLeft { it.toNetworkError() }
    }

    override suspend fun logout(): Either<NetworkError, Response<Message>> {
        return Either.catch {
            val call = authApi.logout()
            if (call.isSuccessful) {
                clearCookies()
                call
            } else {
                throw Exception("Logout failed")
            }
        }.mapLeft { it.toNetworkError() }
    }

    private fun saveCookies(cookies: List<String>) {
        val editor = sharedPreferences.edit()
        for (cookie in cookies) {
            val cookieParts = cookie.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val cookieName = cookieParts[0].split("=".toRegex())[0]
            val cookieValue = cookieParts[0].split("=".toRegex())[1]
            Log.d(cookieName, cookieValue)
            editor.putString(cookieName, cookieValue)
        }
        editor.apply()
    }

    private fun clearCookies() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}