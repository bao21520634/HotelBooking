package com.example.hotelbooking.data.remote.dto

import com.example.hotelbooking.domain.model.Hotel

data class HotelsResponse(
    val data: List<Hotel>,
    val pagination: Pagination
)

data class Pagination(
    val total: Int,
    val page: Int,
    val pages: Int
)