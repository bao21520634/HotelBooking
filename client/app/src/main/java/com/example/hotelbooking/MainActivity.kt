package com.example.hotelbooking

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.hotelbooking.ui.model.sampleData
import com.example.hotelbooking.ui.theme.HotelBookingTheme
import com.example.hotelbooking.view.homepage.HomePageResultScreen
import com.google.android.gms.location.FusedLocationProviderClient


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            HotelBookingTheme {
                HomePageResultScreen(hotelList = sampleData)
            }
        }
    }
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    //chuyen init vao man hinh location
    //fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

    @SuppressLint("MissingPermission")
    private fun getLastUserLocation(
        onGetLastLocationSuccess: (Pair<Double, Double>) -> Unit,
        onGetLastLocationFailed: (Exception) -> Unit
    ) {
        // Check if location permissions are granted
        if (areLocationPermissionsGranted()) {
            // Retrieve the last known location
            fusedLocationProviderClient.lastLocation
                .addOnSuccessListener { location ->
                    location?.let {
                        // If location is not null, invoke the success callback with latitude and longitude
                        onGetLastLocationSuccess(Pair(it.latitude, it.longitude))
                    }
                }
                .addOnFailureListener { exception ->
                    // If an error occurs, invoke the failure callback with the exception
                    onGetLastLocationFailed(exception)
                }
        }
    }
    private fun areLocationPermissionsGranted(): Boolean {
        return (ActivityCompat.checkSelfPermission(
            this, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    this, Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED)
    }

}

