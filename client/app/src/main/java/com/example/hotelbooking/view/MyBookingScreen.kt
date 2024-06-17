package com.example.hotelbooking.view

import HotelCard
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hotelbooking.navigation.Route
import com.example.hotelbooking.ui.utility.AppBar
import com.example.hotelbooking.ui.theme.PrimaryColor
import com.example.hotelbooking.view.components.HotelsViewState
import com.example.hotelbooking.view.components.ProfileViewState
import com.example.hotelbooking.viewmodel.HotelsViewModel
import com.example.hotelbooking.viewmodel.UsersViewModel

@Composable
internal fun MyBookingScreen(
    navController: NavController = rememberNavController(),
    openDetailsScreen: (String) -> Unit = {  },
    hotelsViewModel: HotelsViewModel = hiltViewModel(),
    usersViewModel: UsersViewModel = hiltViewModel()

) {
    val hotelsState by hotelsViewModel.hotelsState.collectAsStateWithLifecycle()

    val userState by usersViewModel.state.collectAsStateWithLifecycle()

    MyBookingContent(
        userState,
        usersViewModel,
        navController,
        hotelsState,
        hotelsViewModel,
        openDetailsScreen
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBookingContent(
    userState: ProfileViewState,
    usersViewModel: UsersViewModel = hiltViewModel(),
    navController: NavController = rememberNavController(),
    hotelsState: HotelsViewState,
    hotelsViewModel: HotelsViewModel = hiltViewModel(),
    openDetailsScreen: (String) -> Unit = { id -> navController.navigate("${Route.DetailsScreen.route}/$id") },
) {
    var showHiredList by remember { mutableStateOf(true) }

    if (showHiredList) {
        LaunchedEffect(hotelsState) {
            hotelsViewModel.getMyBookings()
            usersViewModel.getUser()
        }
    } else {
        LaunchedEffect(hotelsState) {
            hotelsViewModel.getMyHistory()
            usersViewModel.getUser()
        }
    }

    Scaffold(
        topBar = {
            AppBar(
                currentScreen = Route.MyBookingsScreen,
                currentScreenName = "Danh sách của bạn",
                canNavigateBack = false,
                navigateUp = { /*TODO*/ })

        },
    ) { innerpadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerpadding)
                .padding(start = 8.dp, end = 8.dp, top = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
            ) {
                Button(
                    onClick = { showHiredList = true },
                    colors = if (showHiredList) ButtonDefaults.buttonColors(PrimaryColor) else ButtonDefaults.textButtonColors()
                ) {
                    Text(text = "Đã thuê")

                }
                Spacer(modifier = Modifier.width(10.dp))

                Button(
                    onClick = { showHiredList = false },
                    colors = if (!showHiredList) ButtonDefaults.buttonColors(PrimaryColor) else ButtonDefaults.textButtonColors()
                ) {
                    Text(text = "Đã xem")
                }
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(hotelsState.hotels) {hotel ->
                    var isFavored = userState.user?.favorites?.contains(hotel._id) ?: false
                    LaunchedEffect(userState.user) {
                        isFavored = userState.user?.favorites?.contains(hotel._id) ?: false
                    }

                    HotelCard(
                        hotel = hotel,
                        isFavored = isFavored,
                        onClick = { openDetailsScreen(hotel._id) },
                        onFavoriteToggle = {
                            usersViewModel.favorite(hotel._id)
                            isFavored = !isFavored
                        }
                    )
                }
            }
        }
    }
}