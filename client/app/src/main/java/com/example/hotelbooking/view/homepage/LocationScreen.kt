
import android.Manifest
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.hotelbooking.ui.utility.LocationUtil

@Composable
fun LocationScreen() {
    val context = LocalContext.current
    val locationUtil = remember { LocationUtil(context) }
    var location by remember { mutableStateOf<Pair<Double, Double>?>(null) }
    var error by remember { mutableStateOf<Exception?>(null) }

    val locationPermissionRequest = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted: Boolean ->
            if (isGranted) {
                locationUtil.getLastUserLocation(
                    onGetLastLocationSuccess = { lat, lon ->
                        location = Pair(lat, lon)
                    },
                    onGetLastLocationFailed = { exception ->
                        error = exception
                    }
                )
            } else {
                Toast.makeText(context, "Location permission denied", Toast.LENGTH_LONG).show()
            }
        }
    )

    LaunchedEffect(Unit) {
        if (locationUtil.areLocationPermissionsGranted()) {
            locationUtil.getLastUserLocation(
                onGetLastLocationSuccess = { lat, lon ->
                    location = Pair(lat, lon)
                },
                onGetLastLocationFailed = { exception ->
                    error = exception
                }
            )
        } else {
            locationPermissionRequest.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        location?.let {
            Text(text = "Latitude: ${it.first}, Longitude: ${it.second}")
        }
        error?.let {
            Toast.makeText(context, "Failed to get location: ${it.message}", Toast.LENGTH_LONG).show()
        }
        Button(onClick = {
            if (locationUtil.areLocationPermissionsGranted()) {
                locationUtil.getLastUserLocation(
                    onGetLastLocationSuccess = { lat, lon ->
                        location = Pair(lat, lon)
                    },
                    onGetLastLocationFailed = { exception ->
                        error = exception
                    }
                )
            } else {
                locationPermissionRequest.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }) {
            Text("Get Location")
        }
    }
}
