package com.example.hotelbooking.view

import HotelCard
import androidx.compose.foundation.layout.Arrangement
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
import com.example.hotelbooking.navigation.Route
import com.example.hotelbooking.ui.model.Hotel
import com.example.hotelbooking.ui.model.sampleData
import com.example.hotelbooking.ui.utility.AppBar

@Composable
fun FavoriteScreen(favoriteHotelList: List<Hotel>){
    Scaffold(
        topBar = {
            AppBar(
                currentScreen = Route.FavoriteScreen,
                currentScreenName = "Yêu thích",
                canNavigateBack = false,
                navigateUp = { /*TODO*/ })
        },
        bottomBar = {
            BottomNavigationBar()
        }
    ) {innerpadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerpadding)
                .padding(start = 8.dp, end = 8.dp,top = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(favoriteHotelList) {
                HotelCard(hotel = it)
            }
        }
    }
}

@Preview
@Composable
fun FavoriteScreenPreview(){
    FavoriteScreen(favoriteHotelList = sampleData)
}