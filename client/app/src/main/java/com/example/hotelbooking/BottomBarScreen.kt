package com.example.hotelbooking

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen (
    val route: String,
    val icon: ImageVector
){
    data object Home: BottomBarScreen(
        route = "home",
        icon = Icons.Outlined.Home
    )
    data object Favorite: BottomBarScreen(
        route = "favorite",
        icon = Icons.Outlined.FavoriteBorder
    )
    data object MyBooking: BottomBarScreen(
        route = "myBooking",
        icon = Icons.Outlined.List
    )
    data object Profile: BottomBarScreen(
        route = "profile",
        icon = Icons.Outlined.AccountCircle
    )
}