package com.example.hotelbooking.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.hotelbooking.view.auth.ForgotPassWordScreen
import com.example.hotelbooking.view.auth.LoginScreen
import com.example.hotelbooking.view.auth.ResetPasswordScreen
import com.example.hotelbooking.view.auth.SignUpScreen

fun NavGraphBuilder.authNavGraph(navController: NavController) {
    navigation(
        route = Route.AuthRoute.route,
        startDestination = Route.LogInScreen.route
    ) {
        composable(route = Route.LogInScreen.route) {
            LoginScreen(
                openHomeScreen = {
                    navController.navigate(Route.HomeScreen.route)
                }, openSignUpScreen = {
                    navController.navigate(Route.SignUpScreen.route)
                },
                openForgotPasswordScreen = {
                    navController.navigate(Route.ForgotPassword.route)
                }
            )
        }
        composable(route = Route.SignUpScreen.route) {
            SignUpScreen(openLoginScreen = {
                navController.navigate(Route.LogInScreen.route)
            })
        }
        composable(route = Route.ForgotPassword.route) {
            ForgotPassWordScreen(openResetPasswordScreen = {
                navController.navigate(Route.ResetPassword.route)
            })
        }
        composable(route = Route.ResetPassword.route) {
            ResetPasswordScreen(openLoginScreen = {
                navController.navigate(Route.LogInScreen.route)
            })
        }
    }
}


