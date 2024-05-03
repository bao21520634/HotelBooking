package com.example.hotelbooking.navigation

import androidx.annotation.StringRes
import com.example.hotelbooking.R

sealed class Route(
    val route: String,
    @StringRes val resourceId: Int
) {
    object OnBoardingScreen : Route(route = "onBoardingScreen", R.string.onboarding_screen)

    object HomeScreen : Route(route = "homeScreen", R.string.homepage_screen)

    object FavoriteScreen : Route(route = "favoriteScreen", R.string.favorite_screen)

    object MyBookingsScreen : Route(route = "myBookingsScreen", R.string.my_bookings_screen)

    object DetailsScreen : Route(route = "detailsScreen", R.string.details_screen)

    object ProfileScreen : Route(route = "profileScreen", R.string.profile_screen)
}