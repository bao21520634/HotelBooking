package com.example.hotelbooking.view.homepage

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.hotelbooking.ui.utility.ImportantButtonMain
import com.example.hotelbooking.ui.utility.TextFieldWithIncrement
import com.example.hotelbooking.view.homepage.components.SearchViewState
import com.example.hotelbooking.view.properties.CommonHeaderText
import com.example.hotelbooking.viewmodel.HotelsViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RoomPickingScreen(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    hotelsViewModel: HotelsViewModel = hiltViewModel(),
    navigateUp: () -> Unit
) {
    val searchState by hotelsViewModel.searchState.collectAsStateWithLifecycle()

    RoomPickingContent(modifier, navController, searchState, hotelsViewModel, navigateUp)
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomPickingContent(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    searchState: SearchViewState,
    hotelsViewModel: HotelsViewModel = hiltViewModel(),
    navigateUp: () -> Unit
) {
    var nofRoom: Int by remember { mutableStateOf(searchState.roomCount) }
    var nofAdult: Int by remember { mutableStateOf(searchState.adultCount) }
    var nofChildren: Int by remember { mutableStateOf(searchState.childCount) }

    val botRoom: Int = 1;
    val botAdult: Int = 1;
    val botChildren: Int = 0;

    var roomDisabled: Boolean by remember { mutableStateOf(true) }
    var adultDisabled: Boolean by remember { mutableStateOf(true) }
    var childrenDisabled: Boolean by remember { mutableStateOf(true) }

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
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                CommonHeaderText(text = "Số phòng")
                Spacer(Modifier.weight(1f))
                TextFieldWithIncrement(
                    value = nofRoom,
                    onIncrementClick = {
                        nofRoom += 1
                        roomDisabled = false
                    },
                    onDecrementClick = {
                        if (nofRoom > botRoom) {
                            nofRoom -= 1;
                            if (nofRoom == botRoom) {
                                roomDisabled = true;
                            }
                        }
                    },
                    isDecDisable = roomDisabled
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                CommonHeaderText(text = "Số người lớn")
                Spacer(Modifier.weight(1f))
                TextFieldWithIncrement(
                    value = nofAdult,
                    onIncrementClick = {
                        nofAdult += 1
                        adultDisabled = false
                    },
                    onDecrementClick = {
                        if (nofAdult > botAdult) {
                            nofAdult -= 1;
                            if (nofAdult == botAdult) {
                                adultDisabled = true;
                            }
                        }
                    },
                    isDecDisable = adultDisabled
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                CommonHeaderText(text = "Số trẻ em")
                Spacer(Modifier.weight(1f))
                TextFieldWithIncrement(
                    value = nofChildren,
                    onIncrementClick = {
                        nofChildren += 1
                        childrenDisabled = false
                    },
                    onDecrementClick = {
                        if (nofChildren > botChildren) {
                            nofChildren -= 1;
                            if (nofChildren == botChildren) {
                                childrenDisabled = true;
                            }
                        }
                    },
                    isDecDisable = childrenDisabled
                )
            }

            Spacer(modifier = Modifier.weight(1f))
            ImportantButtonMain(text = "Xác nhận", onClick = {
                hotelsViewModel.updateSearchParams(
                    childCount = nofChildren,
                    adultCount = nofAdult,
                    roomCount = nofRoom
                )

                navigateUp()
            })
        }
    }
}
