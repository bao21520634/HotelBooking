package com.example.hotelbooking.view.homepage

import HotelCard
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hotelbooking.R
import com.example.hotelbooking.navigation.Route
import com.example.hotelbooking.ui.model.Hotel
import com.example.hotelbooking.ui.model.sampleData
import com.example.hotelbooking.ui.theme.PrimaryColor
import com.example.hotelbooking.ui.utility.AppBar
import com.example.hotelbooking.ui.utility.reachedBottom
import com.example.hotelbooking.view.properties.CommonBodyText


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePageResultScreen(hotelList: List<Hotel>, modifier: Modifier = Modifier){
    var location: String by remember{ mutableStateOf("Thủ đức, TPHCM") }
    var dateIn: String by remember{ mutableStateOf("18/02/2024") }
    var dateOut: String by remember{ mutableStateOf("25/02/2024") }
    var nofRoom: Int by remember{ mutableStateOf(0) }
    var nofGuest: Int by remember{ mutableStateOf(0) }

    //sample data( now observable)
    val stateHotelList = remember {
        mutableStateListOf<Hotel>()
    }
    val listState = rememberLazyListState();
    val reachedBottom: Boolean by remember {
        derivedStateOf {
            listState.reachedBottom()
        }
    }
    LaunchedEffect(reachedBottom) {
        if (reachedBottom) {
            stateHotelList.addAll(hotelList)
        }
    }

    //dialog
    var openDialog by remember { mutableStateOf(false) }

    if (openDialog) {
        androidx.compose.material3.AlertDialog(
            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside the dialog or on the back
                // button. If you want to disable that functionality, simply use an empty
                // onDismissRequest.
                openDialog = false
            }
        ) {
            Card(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .padding(16.dp),
                shape = MaterialTheme.shapes.large
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    TextButton(onClick = {
                        val sortedList = stateHotelList.sortedBy { it.hotelPrice }
                        stateHotelList.clear()
                        stateHotelList.addAll(sortedList)
                        openDialog = false
                    } ) {
                        CommonBodyText(text = "Tăng dần")
                    }
                    Divider(Modifier.width(100.dp))
                    TextButton(onClick = {
                        val sortedList =  stateHotelList.sortedByDescending { it.hotelPrice }
                        stateHotelList.clear()
                        stateHotelList.addAll(sortedList)
                        openDialog = false
                    } ) {
                        CommonBodyText(text = "Giảm dần")
                    }
                }
            }
        }
    }


    Scaffold (
        modifier = modifier,
        topBar = {
            AppBar(
                currentScreen = Route.HomeScreen,
                currentScreenName = "Trang chủ",
                canNavigateBack = false,
                navigateUp = { /*TODO*/ })
        },

    ){ innerPadding->
        LazyColumn(
            state = listState,
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(dimensionResource(id = R.dimen.screenPadding)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.columnPadding)),
        ) {
            item{
                FilterBlock(
                    location = location,
                    onLocationAction = { /*TODO*/ },
                    dateIn = dateIn,
                    onDateInAction = { /*TODO*/ },
                    dateOut = dateOut,
                    onDateOutAction = { /*TODO*/ },
                    nofRoom = nofRoom,
                    nofGuest = nofGuest,
                    onBlockAction = { /*TODO*/ },
                    modifier = Modifier.fillMaxWidth())
            }
            item{
                ResultManipulator(
                    onDeleteClick = {},
                    onFilterClick = {openDialog = true},
                    onSearchClick = {}
                )
            }
            item{
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Dành cho bạn",
                    style = MaterialTheme.typography.titleLarge,
                    color = colorResource(R.color.dark_blue),
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            items(stateHotelList) { index ->
                HotelCard(hotel = index)
            }

        }
        Column(
        ) {
            LazyColumn(
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
            }
        }
    }

}
@Composable
fun ResultManipulator(
    onDeleteClick: ()->Unit,
    onFilterClick: ()->Unit,
    onSearchClick: ()->Unit,
    modifier: Modifier = Modifier
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ){
        OutlinedButton(onClick = onDeleteClick,
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(2.dp, Color.Red),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.Red,
                containerColor = Color.Transparent
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ){
                Icon(
                    Icons.Rounded.Clear,
                    contentDescription = null
                )
                Text(text = "Xóa")
            }
        }
        OutlinedButton(onClick = onFilterClick,
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = PrimaryColor,
                containerColor = Color.Transparent
            ),
            border = BorderStroke(2.dp, PrimaryColor),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ){
                Icon(
                    painter = painterResource(id = R.drawable.baseline_filter_list_24),
                    contentDescription = null
                )
                Text(text = "Sắp xếp")
            }
        }
        OutlinedButton(onClick = onSearchClick,
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = PrimaryColor,
                containerColor = Color.Transparent
            ),
            border = BorderStroke(2.dp, PrimaryColor),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ){
                Icon(
                    painter = painterResource(id = R.drawable.baseline_search_24),
                    contentDescription = null
                )
                Text(text = "Tìm")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePageResultScreenPreview(){
    HomePageResultScreen(sampleData)
}