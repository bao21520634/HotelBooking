package com.example.hotelbooking.view

import HotelCard
import android.content.res.Resources
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
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hotelbooking.BottomNavigationBar
import com.example.hotelbooking.navigation.Route
import com.example.hotelbooking.domain.model.Hotel
import com.example.hotelbooking.ui.utility.AppBar
import com.example.hotelbooking.ui.theme.PrimaryColor
import com.example.hotelbooking.view.homepage.HomePageSearchContent
import com.example.hotelbooking.view.homepage.components.HotelsViewState
import com.example.hotelbooking.viewmodel.HotelsViewModel

@Composable
internal fun MyBookingScreen(
) {
    val viewModel: HotelsViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    MyBookingContent(state = state)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBookingContent(
    state: HotelsViewState,
    navController: NavController = rememberNavController()
) {
    var showHiredList by remember { mutableStateOf(true) }

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
            verticalArrangement = Arrangement.spacedBy(16.dp)
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
//                    .padding(innerpadding)
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(state.hotels) {
                    HotelCard(hotel = it)
                }
            }
        }

    }
}