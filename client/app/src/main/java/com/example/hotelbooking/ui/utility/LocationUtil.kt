package com.example.hotelbooking.ui.utility

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class LocationUtil(private val context: Context) {

    private val fusedLocationProviderClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    fun areLocationPermissionsGranted(): Boolean {
        return ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    @SuppressLint("MissingPermission")
    fun getLastUserLocation(
        onGetLastLocationSuccess: (Double, Double) -> Unit,
        onGetLastLocationFailed: (Exception) -> Unit
    ) {
        if (areLocationPermissionsGranted()) {
            fusedLocationProviderClient.lastLocation
                .addOnSuccessListener { location ->
                    location?.let {
                        onGetLastLocationSuccess(it.latitude, it.longitude)
                    }
                }
                .addOnFailureListener { exception ->
                    onGetLastLocationFailed(exception)
                }
        }
    }
}