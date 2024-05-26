package com.example.hotelbooking.navigation

import android.os.Build
import androidx.annotation.RequiresApi
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
import com.example.hotelbooking.view.homepage.DatePickingScreen
import com.example.hotelbooking.view.homepage.HomePageResultScreen
import com.example.hotelbooking.view.homepage.RoomPickingScreen
import com.example.hotelbooking.view.login.ResetPasswordScreen
import com.example.hotelbooking.view.profile.ProfileEditScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeNavGraph(navController: NavHostController,
                 modifier: Modifier = Modifier){
    NavHost(
        navController= navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.Home.route
    ){
        composable(route = BottomBarScreen.Home.route){
            HomePageSearchScreen(
                hotelList = sampleData,
                openDateScreen = {
                    navController.navigate(Route.HomeDateScreen.route)
                },
                openResultScreen = {
                    navController.navigate(Route.HomeResultScreen.route)
                },
                openRoomScreen = {
                    navController.navigate(Route.HomeRoomScreen.route)
                }
            )
        }

        composable(route = BottomBarScreen.Favorite.route){
            FavoriteScreen(favoriteHotelList = sampleData)
        }

        composable(route = BottomBarScreen.MyBooking.route){
            MyBookingScreen(hiredHotelList = sampleData, viewedHotelList = sampleData)
        }

        composable(route = BottomBarScreen.Profile.route){
            ProfileScreen(
                openEditProfileScreen = {
                    navController.navigate(Route.ProfileEditScreen.route)
                },
                openOwnerScreen = {},
                openPassWordChangeScreen = {
                    navController.navigate(Route.ResetPassword.route)
                },
                logOut = {}
            )
        }

        //Homepage Nav
        composable(route = Route.HomeResultScreen.route){
            HomePageResultScreen(hotelList = sampleData,
                openDateScreen = {
                    navController.navigate(Route.HomeDateScreen.route)},
                openRoomScreen = {
                    navController.navigate(Route.HomeRoomScreen.route)
                }
            )
        }

        composable(route = Route.HomeDateScreen.route){
            DatePickingScreen(onDateInAction = {
                navController.navigate(Route.HomeDateScreen.route)
            }, onDateOutAction = {
                navController.navigate(Route.HomeDateScreen.route)
            })
        }

        composable(route = Route.HomeRoomScreen.route){
            RoomPickingScreen()
            navController.popBackStack()
        }

        //Profile Nav
        composable(route = Route.ProfileEditScreen.route){
            ProfileEditScreen()
        }

        composable(route = Route.ResetPassword.route){
             ResetPasswordScreen{}
        }
    }
}

