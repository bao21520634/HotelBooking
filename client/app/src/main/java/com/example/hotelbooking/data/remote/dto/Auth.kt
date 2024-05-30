package com.example.hotelbooking.data.remote.dto

data class LoginRequest(val email: String, val password: String)

data class RegisterRequest(val email: String, val password: String, val confirmPassword: String)