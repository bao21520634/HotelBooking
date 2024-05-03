package com.example.hotelbooking.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation

object EndPoints {
    const val ID = "id"
}

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.OnBoardingScreen.route
    ) {
        navigation(
            route = Route.HomeScreen.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(route = Route.OnBoardingScreen.route) {
//                val viewModel: OnBoardingViewModel = hiltViewModel()
//                OnBoardingScreen(onEvent = viewModel::onEvent)
            }
        }
    }

}
