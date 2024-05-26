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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.List
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.hotelbooking.R
import com.example.hotelbooking.navigation.Route
import com.example.hotelbooking.ui.theme.PrimaryColor
import com.example.hotelbooking.ui.utility.AppBar
import com.example.hotelbooking.view.homepage.components.HotelsViewState
import com.example.hotelbooking.viewmodel.HotelsViewModel

@Composable
internal fun HomePageResultScreen(
) {
    val viewModel: HotelsViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        viewModel.getTopBookings()
    }

    val state by viewModel.state.collectAsStateWithLifecycle()
    HomePageResultContent(state = state)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePageResultContent(state: HotelsViewState, modifier: Modifier = Modifier){
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
                currentScreenName = stringResource(R.string.homepage_screen),
                canNavigateBack = false,
                navigateUp = { /*TODO*/ })
        },


    ){ innerPadding->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(dimensionResource(id = R.dimen.screenPadding)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.columnPadding),Alignment.CenterVertically),
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
                ResultManipulator()
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
            items(state.hotels) {
                HotelCard(hotel = it)
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
fun ResultManipulator(modifier: Modifier = Modifier){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ){
        OutlinedButton(onClick = { /*TODO*/ },
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
            ){
                Icon(
                    Icons.Rounded.Clear,
                    contentDescription = null
                )
                Text(text = "Xóa")
            }
        }
        OutlinedButton(onClick = { /*TODO*/ },
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
            ){
                Icon(
                    painter = painterResource(id = R.drawable.baseline_filter_list_24),
                    contentDescription = null
                )
                Text(text = "Lọc")
            }
        }
        OutlinedButton(onClick = { /*TODO*/ },
            modifier = Modifier.weight(1.3f),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = PrimaryColor,
                containerColor = Color.Transparent
            ),
            border = BorderStroke(2.dp, PrimaryColor),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ){
                Icon(
                    Icons.Rounded.List,
                    contentDescription = null
                )
                Text(text = "Sắp xếp")
            }
        }
    }
}