package com.example.hotelbooking.view.properties

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.hotelbooking.navigation.Graph
import com.example.hotelbooking.navigation.PropertiesNavBar
import com.example.hotelbooking.navigation.Route

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProNavScreen(navController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = { if(shouldShowBottomBar(navController = navController)){NavBar(navController = navController) }}
    ) { innerPadding ->
        PropertiesNavBar(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun NavBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val screens = listOf(
        Route.PropertiesScreen.route,
        Route.PropertiesInformationScreen.route,
        Route.PropertiesDetailsScreen.route,
        Route.PropertiesPriceScreen.route,
        Route.PropertiesSPhotoscreen.route,
    )
    val currentIndex = screens.indexOf(currentDestination?.route)
    NavigationBar(containerColor = Color.White) {
        if (currentIndex > 0) {
            NavigationBarItem(
                selected = false,
                icon = { Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "Back") },
                onClick = {
                    navController.navigate(screens[currentIndex - 1])
                },
            )
        }

        NavigationBarItem(
            selected = false,
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            onClick = {
                navController.navigate(Route.HomeScreen.route) {
                    popUpTo(Graph.HOME) {
                        inclusive = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )

        if (currentIndex > 0) {
            if (currentIndex < screens.size - 1) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.KeyboardArrowRight, contentDescription = "Next") },
                    selected = false,
                    onClick = {
                        navController.navigate(screens[currentIndex + 1])
                    },
                )
            } else {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Add, contentDescription = "Add") },
                    selected = false,
                    onClick = {},
                )
            }
        }
    }
}

@Composable
fun shouldShowBottomBar(navController: NavHostController): Boolean {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    return when (currentDestination?.route) {
        Route.PropertiesScreen.route,
        Route.PropertiesInformationScreen.route,
        Route.PropertiesDetailsScreen.route,
        Route.PropertiesPriceScreen.route,
        Route.PropertiesSPhotoscreen.route -> true

        else -> false
    }
}