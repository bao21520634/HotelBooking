package com.example.hotelbooking.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.hotelbooking.ui.model.properties
import com.example.hotelbooking.view.properties.PropertiesDetailScreen
import com.example.hotelbooking.view.properties.PropertiesInformationScreen
import com.example.hotelbooking.view.properties.PropertiesPhotoScreen
import com.example.hotelbooking.view.properties.PropertiesPriceScreen
import com.example.hotelbooking.view.properties.PropertiesScreen

@Composable
fun PropertiesNavBar(navController: NavHostController,
                     modifier: Modifier = Modifier
){
    NavHost(
        navController = navController,
        route = Graph.PROPERTIES,
        startDestination = Route.PropertiesScreen.route
    ) {
        composable(route = Route.PropertiesScreen.route){
            PropertiesScreen(properties = properties) {
            }
        }
        composable(route = Route.PropertiesInformationScreen.route){
            PropertiesInformationScreen()
        }
        composable(route = Route.PropertiesSPhotoscreen.route){
            PropertiesPhotoScreen()
        }
        composable(route = Route.PropertiesPriceScreen.route){
            PropertiesPriceScreen()
        }
        composable(route = Route.PropertiesDetailsScreen.route){
            PropertiesDetailScreen()
        }

    }
}