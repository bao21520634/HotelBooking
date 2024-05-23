package com.example.hotelbooking.view.homepage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.hotelbooking.R
import com.example.hotelbooking.navigation.Route
import com.example.hotelbooking.ui.utility.AppBar
import com.example.hotelbooking.ui.utility.TextFieldWithIncrement
import com.example.hotelbooking.view.properties.CommonHeaderText

@Composable
fun RoomPickingScreen() {
    var nofRoom: Int by remember{ mutableStateOf(1) }
    var nofAdult: Int by remember{ mutableStateOf(1) }
    var nofChildern: Int by remember{ mutableStateOf(1) }

    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppBar(
                currentScreen = Route.HomeRoomScreen,
                currentScreenName = stringResource(id = R.string.homepageRoom_screen),
                canNavigateBack = false,
                navigateUp = { /*TODO*/ })
        }
    ){paddingValue ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue)
                .padding(dimensionResource(id = R.dimen.screenPadding)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.columnPadding))
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically
                ){
                CommonHeaderText(text = "Số phòng")
                Spacer(Modifier.weight(1f))
                TextFieldWithIncrement(value = nofRoom, topBoundary = Int.MAX_VALUE, botBoundary = 0)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                CommonHeaderText(text = "Số người lớn")
                Spacer(Modifier.weight(1f))
                TextFieldWithIncrement(value = nofAdult, topBoundary = Int.MAX_VALUE, botBoundary = 0)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                CommonHeaderText(text = "Số trẻ em")
                Spacer(Modifier.weight(1f))
                TextFieldWithIncrement(value = nofChildern, topBoundary = Int.MAX_VALUE, botBoundary = 0)
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun RoomPickingScreenPreview(){
    RoomPickingScreen()
}