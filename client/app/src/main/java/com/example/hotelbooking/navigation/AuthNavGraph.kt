package com.example.hotelbooking.navigation


import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.hotelbooking.view.login.LoginScreen
import com.example.hotelbooking.view.login.SignUpScreen

fun NavGraphBuilder.authNavGraph(navController: NavController) {
    navigation(
        route = Route.AuthRoute.route,
        startDestination = Route.LogInScreen.route
    ) {
        composable(route = Route.LogInScreen.route){
            LoginScreen(openHomeScreen = {
                navController.navigate(Route.HomeScreen.route)
            }, openSignUpScreen = {
                navController.navigate(Route.SignUpScreen.route)
            })
        } 
        composable(route = Route.SignUpScreen.route){
            SignUpScreen(openLoginScreen = {
                navController.navigate(Route.LogInScreen.route)
            })
        }
    }
}


