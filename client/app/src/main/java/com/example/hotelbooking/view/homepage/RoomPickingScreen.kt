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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                currentScreen = Route.HomeScreen,
                currentScreenName = "Số phòng và khách",
                canNavigateBack = false,
                navigateUp = { /*TODO*/ })
        }
    ){paddingValue ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValue).padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
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