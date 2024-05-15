package com.example.hotelbooking.view.homepage

import HotelCard
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hotelbooking.BottomNavigationBar
import com.example.hotelbooking.R
import com.example.hotelbooking.navigation.Route
import com.example.hotelbooking.ui.model.Hotel
import com.example.hotelbooking.ui.model.sampleData
import com.example.hotelbooking.ui.utility.AppBar
import com.example.hotelbooking.ui.utility.ImportantButtonMain


@Composable
fun HomePageSearchScreen(hotelList: List<Hotel>, modifier: Modifier = Modifier){
    var location: String by remember{ mutableStateOf("Thủ đức, TPHCM") }
    var dateIn: String by remember{ mutableStateOf("18/02/2024") }
    var dateOut: String by remember{ mutableStateOf("25/02/2024") }
    var nofRoom: Int by remember{ mutableStateOf(0) }
    var nofGuest: Int by remember{ mutableStateOf(0) }
    Scaffold (
        modifier = modifier,
        topBar = {
            AppBar(
                currentScreen = Route.HomeScreen,
                currentScreenName = stringResource(id = R.string.homepage_screen),
                canNavigateBack = false,
                navigateUp = { /*TODO*/ })
        },
        bottomBar = {
            BottomNavigationBar();
        }

    ){ innerPadding->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(4.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp,Alignment.CenterVertically),
        ) {
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
            ImportantButtonMain(text = "Tìm kiếm", onClick = { /*TODO*/ })
            Text(
                text = "Dành cho bạn",
                style = MaterialTheme.typography.titleLarge,
                color = colorResource(R.color.dark_blue),
                fontWeight = FontWeight.Bold,
            )
            LazyColumn(
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(hotelList) {
                    HotelCard(hotel = it)
                }
            }
        }
    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBlock(location: String, onLocationAction: ()->(Unit),
                dateIn: String, onDateInAction: ()->(Unit),
                dateOut: String, onDateOutAction: ()->(Unit),
                nofRoom: Int,
                nofGuest: Int, onBlockAction: ()->(Unit),
                modifier: Modifier = Modifier){

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        CommonOutlinedButton(value = location, label = "Điểm đến",
            onAction = { /*TODO*/ },
            leadingIconSource = R.drawable.baseline_location_searching_24)

        Row (
            modifier = modifier.height(IntrinsicSize.Min),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Column (
                modifier = modifier.weight(2/3f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){
                CommonOutlinedButton(value = dateIn, label = "Ngày nhận",
                    onAction = { /*TODO*/ },
                    leadingIconSource = R.drawable.baseline_date_range_24)
                CommonOutlinedButton(value = dateOut, label = "Ngày trả",
                    onAction = { /*TODO*/ },
                    leadingIconSource = R.drawable.baseline_date_range_24)
            }
            OutlinedBlock(nofRoom = nofRoom, nofGuest = nofGuest,
                onAction = { /*TODO*/ },
                modifier = Modifier
                    .weight(1 / 3f)
                    .fillMaxHeight()
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun HomePageSearchScreenPreview(){
    HomePageSearchScreen(sampleData)
}