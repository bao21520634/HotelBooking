package com.example.hotelbooking

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.hotelbooking.ui.theme.HotelBookingTheme
import com.example.hotelbooking.view.properties.PropertiesPhotoScreen

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            HotelBookingTheme {
                PropertiesPhotoScreen()
                //NavGraph(navController = rememberNavController())
            }
        }
    }

}

