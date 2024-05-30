package com.example.hotelbooking.view.homepage

import HotelCard
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hotelbooking.R
import com.example.hotelbooking.navigation.Route
import com.example.hotelbooking.ui.utility.AppBar
import com.example.hotelbooking.ui.utility.ImportantButtonMain
import com.example.hotelbooking.view.homepage.components.CommonOutlinedButton
import com.example.hotelbooking.view.components.HotelsViewState
import com.example.hotelbooking.view.homepage.components.OutlinedBlock
import com.example.hotelbooking.viewmodel.HotelsViewModel
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun HomePageSearchScreen(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    openResultScreen: () -> Unit,
    openRoomScreen: () -> Unit,
    openDetailsScreen: (String) -> Unit = { id -> navController.navigate("${Route.DetailsScreen.route}/$id") }
) {
    val viewModel: HotelsViewModel = hiltViewModel()
    val hotelsState by viewModel.hotelsState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getTopBookings()
    }

    HomePageSearchContent(modifier, navController, hotelsState, openResultScreen, openRoomScreen, openDetailsScreen)
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePageSearchContent(
    modifier: Modifier = Modifier,
    navController: NavController,
    hotelsState: HotelsViewState,
    openResultScreen: () -> Unit,
    openRoomScreen: () -> Unit,
    openDetailsScreen: (String) -> Unit = { id -> navController.navigate("${Route.DetailsScreen.route}/$id") }
) {
    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    val current = LocalDateTime.now()
    val nextDay = current.plusDays(1)

    var location: String by remember { mutableStateOf("Thủ đức, TPHCM") }
    var dateIn: String by remember { mutableStateOf(current.format(formatter)) }
    var dateOut: String by remember { mutableStateOf(nextDay.format(formatter)) }
    var nofRoom: Int by remember { mutableStateOf(0) }
    var nofGuest: Int by remember { mutableStateOf(0) }

    val calendarState = rememberSheetState()
    var selectedDateType by remember { mutableStateOf<DateType?>(null) }

    DatePickerDialog(
        calendarState = calendarState,
        selectedDateType = selectedDateType,
        onDateSelected = { selectedDate, dateType ->
            val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
            val formattedDate = selectedDate.format(formatter)
            when (dateType) {
                DateType.IN -> dateIn = formattedDate
                DateType.OUT -> dateOut = formattedDate
            }
        }
    )
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            AppBar(
                currentScreen = Route.HomeScreen,
                currentScreenName = stringResource(id = R.string.homepage_screen),
                canNavigateBack = false,
                navigateUp = { /*TODO*/ })

        },
        ) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(dimensionResource(id = R.dimen.screenPadding)),
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen.columnPadding),
                Alignment.CenterVertically
            ),
        ) {
            item {
                FilterBlock(
                    location = location,
                    onLocationAction = { /*TODO*/ },
                    dateIn = dateIn,
                    onDateInAction = {
                        selectedDateType = DateType.IN
                        calendarState.show()
                    },
                    dateOut = dateOut,
                    onDateOutAction = {
                        selectedDateType = DateType.OUT
                        calendarState.show()
                    },
                    nofRoom = nofRoom,
                    nofGuest = nofGuest,
                    onBlockAction = { openRoomScreen() },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                ImportantButtonMain(text = "Tìm kiếm", onClick = { openResultScreen() })
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Dành cho bạn",
                    style = MaterialTheme.typography.titleLarge,
                    color = colorResource(R.color.dark_blue),
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            item {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                )
                {
                    items(hotelsState.hotels) {
                        HotelCard(
                            hotel = it,
                            onClick = { openDetailsScreen(it._id) },
                            modifier = Modifier.size(width = 328.dp, height = 370.dp))
                    }
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBlock(
    location: String, onLocationAction: () -> Unit,
    dateIn: String, onDateInAction: () -> Unit,
    dateOut: String, onDateOutAction: () -> Unit,
    nofRoom: Int,
    nofGuest: Int, onBlockAction: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        CommonOutlinedButton(
            value = location, label = "Điểm đến",
            onAction = { onLocationAction() },
            leadingIconSource = R.drawable.baseline_location_searching_24
        )

        Row(
            modifier = modifier.height(IntrinsicSize.Min),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = modifier.weight(3 / 5f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CommonOutlinedButton(
                    value = dateIn, label = "Ngày nhận",
                    onAction = { onDateInAction() },
                    leadingIconSource = R.drawable.baseline_date_range_24
                )
                CommonOutlinedButton(
                    value = dateOut, label = "Ngày trả",
                    onAction = { onDateOutAction() },
                    leadingIconSource = R.drawable.baseline_date_range_24
                )
            }
            OutlinedBlock(
                nofRoom = nofRoom, nofGuest = nofGuest,
                onAction = { onBlockAction() },
                modifier = Modifier
                    .weight(2 / 5f)
                    .fillMaxHeight()
            )
        }
    }
}
