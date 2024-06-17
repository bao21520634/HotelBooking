package com.example.hotelbooking.view.homepage

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hotelbooking.R
import com.example.hotelbooking.navigation.Route
import com.example.hotelbooking.ui.utility.AppBar
import com.example.hotelbooking.ui.utility.InfoTextField
import com.example.hotelbooking.view.homepage.components.PlaceViewState
import com.example.hotelbooking.viewmodel.HotelsViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun HomePageLocationScreen(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    hotelsViewModel: HotelsViewModel = hiltViewModel(),
    navigateUp: () -> Unit
) {
    val placesState by hotelsViewModel.placesState.collectAsStateWithLifecycle()

    HomePageLocationContent(modifier, navController, placesState, hotelsViewModel, navigateUp)
}

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun HomePageLocationContent(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    placesState: PlaceViewState,
    hotelsViewModel: HotelsViewModel = hiltViewModel(),
    navigateUp: () -> Unit
) {
    var location: String by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppBar(
                currentScreen = Route.HomeRoomScreen,
                currentScreenName = "",
                canNavigateBack = true,
                navigateUp = navigateUp
            )
        }
    ) { paddingValue ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue)
                .padding(dimensionResource(id = R.dimen.screenPadding)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.columnPadding))
        ) {
            InfoTextField(
                value = location,
                onValueChange = {
                    location = it

                    hotelsViewModel.getLocationPredictions(location)
                },
                promptText = "Nơi bạn muốn tìm",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )

            LazyColumn {
                items(placesState.predictionPlaces) {
                    SearchResult(text = it.place_name, onClick = {
                        hotelsViewModel.updateSearchParams(
                            location = it.place_name,
                            location_id = it.place_id
                        )

                        navigateUp()
                    })
                }
            }
        }
    }
}

@Composable
fun SearchResult(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.outline_location_on_24),
                contentDescription = "Location",
                tint = colorResource(id = R.color.primary)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = text, fontSize = 14.sp)
        }
    }
}