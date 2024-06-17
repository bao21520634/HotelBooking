package com.example.hotelbooking.view

import HotelCard
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hotelbooking.R
import com.example.hotelbooking.navigation.Route
import com.example.hotelbooking.ui.utility.AppBar
import com.example.hotelbooking.view.components.HotelsViewState
import com.example.hotelbooking.view.components.ProfileViewState
import com.example.hotelbooking.viewmodel.HotelsViewModel
import com.example.hotelbooking.viewmodel.UsersViewModel

@Composable
internal fun FavoriteScreen(
    navController: NavController = rememberNavController(),
    openDetailsScreen: (String) -> Unit = { },
    hotelsViewModel: HotelsViewModel = hiltViewModel(),
    usersViewModel: UsersViewModel = hiltViewModel()
) {
    val hotelsState by hotelsViewModel.hotelsState.collectAsStateWithLifecycle()

    val userState by usersViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        hotelsViewModel.getMyFavorites()
        usersViewModel.getUser()
    }

    FavoriteContent(userState, usersViewModel, navController, hotelsState, openDetailsScreen)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteContent(
    userState: ProfileViewState,
    usersViewModel: UsersViewModel = hiltViewModel(),
    navController: NavController,
    hotelsState: HotelsViewState,
    openDetailsScreen: (String) -> Unit = { }
) {
    Scaffold(
        topBar = {
            AppBar(
                currentScreen = Route.FavoriteScreen,
                currentScreenName = stringResource(id = R.string.favorite_screen),
                canNavigateBack = false,
                navigateUp = { /*TODO*/ })
        },
    ) { innerpadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerpadding)
                .padding(dimensionResource(id = R.dimen.screenPadding)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.itemInListPadding)),
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