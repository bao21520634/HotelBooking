package com.example.hotelbooking.domain.model

data class User(
    val _id: String,
    val avatar: String,
    val email: String,
    val favorites: List<Any>,
    val history: List<Any>,
    val username: String,
    val gender: String,
    val bio: String,
    val search: List<Search>
)

data class Search(
    val _id: String,
    val city: String
)