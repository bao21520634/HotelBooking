package com.example.hotelbooking.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.hotelbooking.BottomBarScreen
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
            HomePageSearchScreen()
        }
        composable(route = BottomBarScreen.Favorite.route){
            FavoriteScreen()
        }
        composable(route = BottomBarScreen.MyBooking.route){
            MyBookingScreen()
        }
        composable(route = BottomBarScreen.Profile.route){
            ProfileScreen()
        }
    }
}

