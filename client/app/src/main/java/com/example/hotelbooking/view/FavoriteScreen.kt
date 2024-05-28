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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.hotelbooking.R
import com.example.hotelbooking.navigation.Route
import com.example.hotelbooking.ui.model.Hotel
import com.example.hotelbooking.ui.model.sampleData
import com.example.hotelbooking.ui.utility.AppBar

@Composable
fun FavoriteScreen(favoriteHotelList: List<Hotel>, openDetailsScreen: () -> Unit){
    Scaffold(
        topBar = {
            AppBar(
                currentScreen = Route.FavoriteScreen,
                currentScreenName = stringResource(id = R.string.favorite_screen),
                canNavigateBack = false,
                navigateUp = { /*TODO*/ })
        },
    ) {innerpadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerpadding)
                .padding(dimensionResource(id = R.dimen.screenPadding)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.itemInListPadding))
        ) {
            items(favoriteHotelList) {
                HotelCard(hotel = it, onClick = {openDetailsScreen()})
            }
        }
    }
}

@Preview
@Composable
fun FavoriteScreenPreview(){
    FavoriteScreen(favoriteHotelList = sampleData, openDetailsScreen = {})
}