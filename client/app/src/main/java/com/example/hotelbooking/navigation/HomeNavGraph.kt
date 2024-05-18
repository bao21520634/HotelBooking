package com.example.hotelbooking.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.hotelbooking.BottomBarScreen
import com.example.hotelbooking.ui.model.sampleData
import com.example.hotelbooking.view.FavoriteScreen
import com.example.hotelbooking.view.MyBookingScreen
import com.example.hotelbooking.view.homepage.HomePageSearchScreen
import com.example.hotelbooking.view.profile.ProfileScreen
import androidx.compose.ui.Modifier

@Composable
fun HomeNavGraph(navController: NavHostController,
                 modifier: Modifier = Modifier){
    NavHost(
        navController= navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.Home.route
    ){
        composable(route = BottomBarScreen.Home.route){
            HomePageSearchScreen(hotelList = sampleData)
        }
        composable(route = BottomBarScreen.Favorite.route){
            FavoriteScreen(favoriteHotelList = sampleData)
        }
        composable(route = BottomBarScreen.MyBooking.route){
            MyBookingScreen(hiredHotelList = sampleData, viewedHotelList = sampleData)
        }
        composable(route = BottomBarScreen.Profile.route){
            ProfileScreen()
        }

    }
}