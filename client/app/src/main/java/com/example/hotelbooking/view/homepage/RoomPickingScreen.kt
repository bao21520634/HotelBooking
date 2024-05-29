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
    var nofChildren: Int by remember{ mutableStateOf(0) }

    val botRoom: Int = 1;
    val botAdult: Int = 1;
    val botChildren: Int = 0;

    var roomDisabled: Boolean by remember{ mutableStateOf(true) }
    var adultDisabled: Boolean by remember{ mutableStateOf(true) }
    var childrenDisabled: Boolean by remember{ mutableStateOf(true) }
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
                TextFieldWithIncrement(
                    value = nofRoom,
                    onIncrementClick = {
                        nofRoom+=1
                        roomDisabled = false
                                       },
                    onDecrementClick = {
                        if(nofRoom>botRoom){
                            nofRoom -=1;
                            if(nofRoom==botRoom){
                                roomDisabled = true;
                            }
                        }
                    },
                    isDecDisable = roomDisabled
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                CommonHeaderText(text = "Số người lớn")
                Spacer(Modifier.weight(1f))
                TextFieldWithIncrement(
                    value = nofAdult,
                    onIncrementClick = {
                        nofAdult+=1
                        adultDisabled = false
                    },
                    onDecrementClick = {
                        if(nofAdult>botAdult){
                            nofAdult -=1;
                            if(nofAdult==botAdult){
                                adultDisabled = true;
                            }
                        }
                    },
                    isDecDisable = adultDisabled
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                CommonHeaderText(text = "Số trẻ em")
                Spacer(Modifier.weight(1f))
                TextFieldWithIncrement(
                    value = nofChildren,
                    onIncrementClick = {
                        nofChildren+=1
                        childrenDisabled = false
                    },
                    onDecrementClick = {
                        if(nofChildren>botChildren){
                            nofChildren -=1;
                            if(nofChildren==botChildren){
                                childrenDisabled = true;
                            }
                        }
                    },
                    isDecDisable = childrenDisabled
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun RoomPickingScreenPreview(){
    RoomPickingScreen()
}