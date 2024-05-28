package com.example.hotelbooking.view.properties

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.hotelbooking.navigation.Graph
import com.example.hotelbooking.navigation.PropertiesNavBar
import com.example.hotelbooking.navigation.Route

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProScreen(navController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = { NavBar(navController = navController) }
    ) { innerPadding ->
        PropertiesNavBar(navController = navController, modifier = Modifier.padding(innerPadding))
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

    NavigationBar (containerColor = Color.White) {
        NavigationBarItem(
            selected = false,
            icon = { Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "Back") },
            onClick = { if(currentIndex > 0) {
                navController.navigate(screens[currentIndex - 1]) }
            }
        )

        NavigationBarItem(
            selected = false,
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            onClick = {
                navController.navigate(Route.HomeScreen.route){
                    popUpTo(Graph.HOME){
                        inclusive = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )

        NavigationBarItem(
            icon = { Icon(Icons.Default.KeyboardArrowRight, contentDescription = "Next") },
            selected = false,
            onClick = {
                if (currentIndex < screens.size - 1) {
                    navController.navigate(screens[currentIndex + 1])
                }
            }
        )
    }

}