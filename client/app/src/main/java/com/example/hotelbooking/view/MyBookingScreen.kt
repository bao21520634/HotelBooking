package com.example.hotelbooking.view

import HotelCard
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hotelbooking.BottomNavigationBar
import com.example.hotelbooking.ui.utility.AppBar
import com.example.hotelbooking.navigation.Route
import com.example.hotelbooking.ui.model.Hotel
import com.example.hotelbooking.ui.model.sampleData

@Composable
fun MyBookingScreen(hiredHotelList: List<Hotel>, viewedHotelList: List<Hotel>){
    Scaffold(
        topBar = {
            AppBar(
                currentScreen = Route.MyBookingsScreen,
                currentScreenName = "Danh sách của bạn",
                canNavigateBack = false,
                navigateUp = { /*TODO*/ })
        },
        bottomBar = {
            BottomNavigationBar()
        }
    ) {innerpadding ->
        Column {
            Row(

            ){

            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerpadding)
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(hiredHotelList) {
                HotelCard(hotel = it)
            }
        }
    }
}

@Preview
@Composable
fun MyBookingScreenPreview(){
    MyBookingScreen(hiredHotelList = sampleData, viewedHotelList = sampleData)
}