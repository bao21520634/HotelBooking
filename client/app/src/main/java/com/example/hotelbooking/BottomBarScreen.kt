package com.example.hotelbooking

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.hotelbooking.navigation.Route

sealed class BottomBarScreen (
    val route: String,
    val icon: ImageVector
){
    data object Home: BottomBarScreen(
        route = Route.HomeScreen.route,
        icon = Icons.Outlined.Home
    )
    data object Favorite: BottomBarScreen(
        route = Route.FavoriteScreen.route,
        icon = Icons.Outlined.FavoriteBorder
    )
    data object MyBooking: BottomBarScreen(
        route = Route.MyBookingsScreen.route,
        icon = Icons.Outlined.List
    )
    data object Profile: BottomBarScreen(
        route = Route.ProfileScreen.route,
        icon = Icons.Outlined.AccountCircle
    )
}