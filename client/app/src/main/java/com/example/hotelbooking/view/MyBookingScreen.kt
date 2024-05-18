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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hotelbooking.BottomNavigationBar
import com.example.hotelbooking.navigation.Route
import com.example.hotelbooking.ui.model.Hotel
import com.example.hotelbooking.ui.model.sampleData
import com.example.hotelbooking.ui.utility.AppBar
import com.example.hotelbooking.ui.theme.PrimaryColor

@Composable
fun MyBookingScreen(
    hiredHotelList: List<Hotel>,
    viewedHotelList: List<Hotel>,
){



    Scaffold(
        topBar = {
            AppBar(
                currentScreen = Route.MyBookingsScreen,
                currentScreenName = "Danh sách của bạn",
                canNavigateBack = false,
                navigateUp = { /*TODO*/ })

        },
    ) {innerpadding ->
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
            ){
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(PrimaryColor)
                ) {
                    Text(text = "Đã thuê")

                }
                Spacer(modifier = Modifier.width(10.dp))

                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(PrimaryColor)
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
                items(hiredHotelList) {
                    HotelCard(hotel = it)
                }
            }
        }

    }
}

@Preview
@Composable
fun MyBookingScreenPreview(){
    MyBookingScreen(hiredHotelList = sampleData, viewedHotelList = sampleData)
}