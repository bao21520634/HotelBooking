package com.example.hotelbooking.domain.repository

import arrow.core.Either
import com.example.hotelbooking.domain.model.User
import com.example.hotelbooking.domain.model.NetworkError

interface UserRepository {
    suspend fun getUser(): Either<NetworkError, User>
}