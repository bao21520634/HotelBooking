package com.example.hotelbooking.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.hotelbooking.HomeScreen
import com.example.hotelbooking.view.properties.PropertiesDetailScreen
import com.example.hotelbooking.view.properties.PropertiesInformationScreen
import com.example.hotelbooking.view.properties.PropertiesPhotoScreen
import com.example.hotelbooking.view.properties.PropertiesPriceScreen
import com.example.hotelbooking.view.properties.PropertiesScreen
import com.example.hotelbooking.viewmodel.HotelsViewModel
import com.example.hotelbooking.viewmodel.UsersViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PropertiesNavBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    hotelsViewModel: HotelsViewModel = hiltViewModel(),
    usersViewModel: UsersViewModel = hiltViewModel(),
) {
    NavHost(
        navController = navController,
        route = Graph.PROPERTIES,
        startDestination = Route.PropertiesScreen.route
    ) {
        composable(route = Route.PropertiesScreen.route) {
            PropertiesScreen(hotelsViewModel = hotelsViewModel, usersViewModel = usersViewModel, onCreate = {
                navController.navigate(Route.PropertiesInformationScreen.route)
            })
        }
        composable(route = Route.PropertiesInformationScreen.route) {
            PropertiesInformationScreen(hotelsViewModel = hotelsViewModel, usersViewModel = usersViewModel, )
        }
        composable(route = Route.PropertiesSPhotoscreen.route) {
            PropertiesPhotoScreen(hotelsViewModel = hotelsViewModel, usersViewModel = usersViewModel, )
        }
        composable(route = Route.PropertiesPriceScreen.route) {
            PropertiesPriceScreen(hotelsViewModel = hotelsViewModel, usersViewModel = usersViewModel, )
        }
        composable(route = Route.PropertiesDetailsScreen.route) {
            PropertiesDetailScreen(hotelsViewModel = hotelsViewModel, usersViewModel = usersViewModel, )
        }

        composable(route = Route.HomeScreen.route) {
            HomeScreen()
        }
    }
}