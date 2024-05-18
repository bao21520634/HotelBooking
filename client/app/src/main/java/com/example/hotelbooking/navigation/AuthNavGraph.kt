package com.example.hotelbooking.navigation


import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.hotelbooking.view.login.LoginScreen
import com.example.hotelbooking.view.login.SignUpScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ){
        composable(route = AuthScreen.Login.route){
            LoginScreen(
                openSignUpScreen = { navController.navigate(AuthScreen.SignUp.route)},
                openHomeScreen = {
                    navController.popBackStack()
                    navController.navigate(Graph.HOME)})
        }
        composable(route = AuthScreen.SignUp.route){
            SignUpScreen(
                openLogInScreen = {navController.navigate(AuthScreen.Login.route)}
            )
        }
//        composable(route = AuthScreen.Forgot.route){
//
//        }

    }
}

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen(route = "LOGIN")
    object SignUp : AuthScreen(route = "SIGN_UP")
//    object Forgot : AuthScreen(route = "FORGOT")
}