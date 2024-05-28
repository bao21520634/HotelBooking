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
import com.example.hotelbooking.R
import com.example.hotelbooking.ui.model.Hotel
import com.example.hotelbooking.view.DetailScreen
import com.example.hotelbooking.view.homepage.HomePageResultScreen
import com.example.hotelbooking.view.homepage.RoomPickingScreen
import com.example.hotelbooking.view.login.LoginScreen
import com.example.hotelbooking.view.login.ResetPasswordScreen
import com.example.hotelbooking.view.profile.ProfileEditScreen
import com.example.hotelbooking.navigation.authNavGraph
import com.example.hotelbooking.ui.model.properties
import com.example.hotelbooking.view.properties.ProScreen
import com.example.hotelbooking.view.properties.PropertiesScreen
import kotlinx.coroutines.handleCoroutineException

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

                openResultScreen = {
                    navController.navigate(Route.HomeResultScreen.route)
                },
                openRoomScreen = {
                    navController.navigate(Route.HomeRoomScreen.route)
                },
                openDetailsScreen = {
                    navController.navigate(Route.DetailsScreen.route)
                }
            )
        }

        composable(route = BottomBarScreen.Favorite.route){
            FavoriteScreen(favoriteHotelList = sampleData, openDetailsScreen = {
                navController.navigate(Route.DetailsScreen.route)
            })
        }

        composable(route = BottomBarScreen.MyBooking.route){
            MyBookingScreen(hiredHotelList = sampleData, viewedHotelList = sampleData,
                openDetailsScreen = {
                    navController.navigate(Route.DetailsScreen.route)
                })
        }

        composable(route = BottomBarScreen.Profile.route){
            ProfileScreen(
                openEditProfileScreen = {
                    navController.navigate(Route.ProfileEditScreen.route)
                },
                
                openPropertiesScreen = {
                    navController.navigate(Route.PropertiesScreen.route)
                },
                
                openPassWordChangeScreen = {
                    navController.navigate(Route.ResetPassword.route)
                },
                
                logOut = {
                    navController.navigate(Route.AuthRoute.route){
                        popUpTo(Graph.ROOT) {inclusive = true}
                    }
                }
            )
        }

        //Homepage Nav
        composable(route = Route.HomeResultScreen.route){
            HomePageResultScreen(hotelList = sampleData,
                openDateScreen = {
                    navController.navigate(Route.HomeDateScreen.route)},
                openRoomScreen = {
                    navController.navigate(Route.HomeRoomScreen.route)
                },
                openDetailsScreen = {
                    navController.navigate(Route.DetailsScreen.route)
                },
                backHomeScreen = {
                    navController.navigate(Route.HomeScreen.route)
                }
            )
        }

        composable(route = Route.HomeRoomScreen.route){
            RoomPickingScreen()
        }
        
        composable(route = Route.DetailsScreen.route){
            DetailScreen(hotel = Hotel(hotelThumbnail = R.drawable.hotel_thumbnail,
                hotelName = "Cityscape Suites",
                starRating = 4.2f,
                commentRating = 8.5f,
                numberOfComment = 220,
                hotelAddress = "789 Downtown Ave, New York, NY",
                numberOfBed = 1,
                hotelPrice = 250,
                isFeatured = true)
            )
        }

        //Profile Nav
        composable(route = Route.ProfileEditScreen.route){
            ProfileEditScreen()
        }

        composable(route = Route.ResetPassword.route){
             ResetPasswordScreen{}
        }

        authNavGraph(navController)
        
        composable(route = Route.PropertiesScreen.route){
            ProScreen()
        }
    }

}

