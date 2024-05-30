package com.example.hotelbooking.navigation

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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
import androidx.navigation.navArgument
import com.example.hotelbooking.view.auth.ResetPasswordScreen
import com.example.hotelbooking.domain.model.Hotel
import com.example.hotelbooking.view.DetailScreen
import com.example.hotelbooking.view.homepage.HomePageLocationScreen
import com.example.hotelbooking.view.homepage.HomePageResultScreen
import com.example.hotelbooking.view.homepage.RoomPickingScreen
import com.example.hotelbooking.view.profile.ProfileEditScreen
import com.example.hotelbooking.view.properties.ProNavScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomePageSearchScreen(
                openResultScreen = {
                    navController.navigate(Route.HomeResultScreen.route)
                },
                openRoomScreen = {
                    navController.navigate(Route.HomeRoomScreen.route)
                },
                openLocationScreen = {
                    navController.navigate(Route.HomeLocationScreen.route)
                },
                openDetailsScreen = { id: String ->
                    navController.navigate("${Route.DetailsScreen.route}/$id")
                }
            )
        }

        composable(route = BottomBarScreen.Favorite.route) {
            FavoriteScreen(
                openDetailsScreen = { id: String ->
                    navController.navigate("${Route.DetailsScreen.route}/$id")
                }
            )
        }

        composable(route = BottomBarScreen.MyBooking.route) {
            MyBookingScreen(
                openDetailsScreen = { id: String ->
                    navController.navigate("${Route.DetailsScreen.route}/$id")
                }
            )
        }

        composable(route = BottomBarScreen.Profile.route) {
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
                    navController.navigate(Route.AuthRoute.route) {
                        popUpTo(Graph.ROOT) { inclusive = true }
                    }
                }
            )
        }

        //Homepage Nav
        composable(route = Route.HomeResultScreen.route) {
            HomePageResultScreen(
                openDateScreen = {
                    navController.navigate(Route.HomeDateScreen.route)
                },
                openRoomScreen = {
                    navController.navigate(Route.HomeRoomScreen.route)
                },
                openDetailsScreen = { id: String ->
                    navController.navigate("${Route.DetailsScreen.route}/$id")
                },
                backHomeScreen = {
                    navController.navigate(Route.HomeScreen.route)
                }
            )
        }

        composable(route = Route.HomeRoomScreen.route) {
            RoomPickingScreen()
        }

        composable(route = Route.HomeLocationScreen.route) {
            HomePageLocationScreen()
        }

        composable(
            route = "${Route.DetailsScreen.route}/{id}",
            arguments = listOf(navArgument("id") { defaultValue = "defaultId" })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            DetailScreen(id = id ?: "defaultId")
        }

        //Profile Nav
        composable(route = Route.ProfileEditScreen.route) {
            ProfileEditScreen()
        }

        composable(route = Route.ResetPassword.route) {
            ResetPasswordScreen(openLoginScreen = {})
        }

        authNavGraph(navController)

        composable(route = Route.PropertiesScreen.route) {
            ProNavScreen()
        }
    }
}

