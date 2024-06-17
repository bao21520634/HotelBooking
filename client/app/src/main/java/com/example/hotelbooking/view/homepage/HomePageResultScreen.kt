package com.example.hotelbooking.view.homepage

import HotelCard
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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
import androidx.compose.material.icons.rounded.List
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hotelbooking.R
import com.example.hotelbooking.domain.model.Hotel
import com.example.hotelbooking.navigation.Route
import com.example.hotelbooking.ui.theme.PrimaryColor
import com.example.hotelbooking.ui.utility.AppBar
import com.example.hotelbooking.ui.utility.reachedBottom
import com.example.hotelbooking.view.components.HotelsViewState
import com.example.hotelbooking.view.components.ProfileViewState
import com.example.hotelbooking.view.homepage.components.SearchViewState
import com.example.hotelbooking.view.properties.CommonBodyText
import com.example.hotelbooking.view.util.components.LoadingDialog
import com.example.hotelbooking.viewmodel.HotelsViewModel
import com.example.hotelbooking.viewmodel.UsersViewModel
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomePageResultScreen(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    hotelsViewModel: HotelsViewModel = hiltViewModel(),
    usersViewModel: UsersViewModel = hiltViewModel(),
    openRoomScreen: () -> Unit,
    openDetailsScreen: (String) -> Unit = {},
    backHomeScreen: () -> Unit
) {
    val hotelsState by hotelsViewModel.hotelsState.collectAsStateWithLifecycle()

    val searchState by hotelsViewModel.searchState.collectAsStateWithLifecycle()

    val userState by usersViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        usersViewModel.getUser()
    }

    HomePageResultContent(
        modifier,
        searchState,
        hotelsState,
        hotelsViewModel,
        navController,
        userState,
        usersViewModel,
        openRoomScreen,
        openDetailsScreen,
        backHomeScreen
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePageResultContent(
    modifier: Modifier = Modifier,
    searchState: SearchViewState,
    hotelsState: HotelsViewState,
    hotelsViewModel: HotelsViewModel = hiltViewModel(),
    navController: NavController = rememberNavController(),
    userState: ProfileViewState,
    usersViewModel: UsersViewModel = hiltViewModel(),
    openRoomScreen: () -> Unit,
    openDetailsScreen: (String) -> Unit = { },
    backHomeScreen: () -> Unit
) {

    var location: String by remember { mutableStateOf(searchState.location) }
    var dateIn: String by remember { mutableStateOf(searchState.checkIn) }
    var dateOut: String by remember { mutableStateOf(searchState.checkOut) }
    var nofRoom: Int by remember { mutableStateOf(searchState.roomCount) }
    var nofGuest: Int by remember { mutableStateOf(searchState.adultCount + searchState.childCount) }

    val searchParams: HashMap<String, String> = HashMap()

    val stateHotelList = remember {
        mutableStateListOf(*hotelsState.hotels.toTypedArray())
    }

    LaunchedEffect(searchState) {
        location = searchState.location
        dateIn = searchState.checkIn
        dateOut = searchState.checkOut
        nofRoom = searchState.roomCount
        nofGuest = searchState.adultCount + searchState.childCount


        val checkIn =  LocalDate.parse(searchState.checkIn, DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString()

        val checkOut = LocalDate.parse(searchState.checkOut, DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString()

        searchParams["place_id"] = searchState.location_id
        searchParams["checkIn"] = checkIn
        searchParams["checkOut"] = checkOut
        searchParams["adultCount"] = searchState.adultCount.toString()
        searchParams["childCount"] = searchState.childCount.toString()
        searchParams["roomCount"] = searchState.roomCount.toString()
        searchParams["sortOption"] = searchState.sortOption
        searchParams["page"] = "1"
    }

    LaunchedEffect(dateIn, dateOut) {
        hotelsViewModel.updateSearchParams(checkIn = dateIn, checkOut = dateOut)
    }

    LaunchedEffect(hotelsState) {
        stateHotelList.addAll(hotelsState.hotels)
    }

    val calendarState = rememberSheetState()
    var selectedDateType by remember { mutableStateOf<DateType?>(null) }

    val listState = rememberLazyListState();
    val reachedBottom: Boolean by remember {
        derivedStateOf {
            listState.reachedBottom()
        }
    }

    LaunchedEffect(reachedBottom) {
        if (reachedBottom) {
            stateHotelList.addAll(hotelsState.hotels)
        }
    }
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
                    .padding(16.dp)
                    .shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(8.dp),
                        clip = true
                    ),
                shape = MaterialTheme.shapes.extraLarge,
                colors = CardDefaults.cardColors(colorResource(id = R.color.white))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    TextButton(onClick = {
                        hotelsViewModel.updateSearchParams(sortOption = "starRating")
                        openDialog = false
                    }) {
                        CommonBodyText(text = "Mức sao", color = colorResource(id = R.color.primary))
                    }
                    TextButton(onClick = {
                        hotelsViewModel.updateSearchParams(sortOption = "pricePerNightAsc")
                        openDialog = false
                    }) {
                        CommonBodyText(text = "Tăng dần", color = colorResource(id = R.color.primary))
                    }
                    TextButton(onClick = {
                        hotelsViewModel.updateSearchParams(sortOption = "pricePerNightDesc")
                        openDialog = false
                    }) {
                        CommonBodyText(text = "Giảm dần", color = colorResource(id = R.color.primary))
                    }
                }
            }
        }
    }

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
        },
        minDate = if (selectedDateType == DateType.IN) LocalDate.parse(
            dateIn,
            DateTimeFormatter.ofPattern("dd-MM-yyyy")
        ) else null
    )

    Scaffold(
        modifier = modifier,
        topBar = {
            AppBar(
                currentScreen = Route.HomeScreen,
                currentScreenName = stringResource(R.string.homepage_screen),
                canNavigateBack = false,
                navigateUp = backHomeScreen
            )
        },


        ) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(dimensionResource(id = R.dimen.screenPadding)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.columnPadding)),
            horizontalAlignment = Alignment.CenterHorizontally
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
                ResultManipulator(
                    onDeleteClick = backHomeScreen,
                    onFilterClick = { openDialog = true },
                    onSearchClick = { hotelsViewModel.search(searchParams) },
                )
            }
            items(hotelsState.hotels) { hotel ->
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

@Composable
fun ResultManipulator(
    modifier: Modifier = Modifier,
    onDeleteClick: () -> Unit,
    onFilterClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedButton(
            onClick = onDeleteClick,
            modifier = Modifier
                .weight(1f),
            border = BorderStroke(2.dp, colorResource(id = R.color.red)),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = colorResource(id = R.color.red),
                containerColor = Color.Transparent
            ),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    Icons.Rounded.Clear,
                    contentDescription = null
                )
                Text(text = "Xóa")
            }
        }
        OutlinedButton(
            onClick = onFilterClick,
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
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_filter_list_24),
                    contentDescription = null
                )
                Text(text = "Lọc")
            }
        }

        OutlinedButton(
            onClick = onSearchClick,
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
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_search_24),
                    contentDescription = null
                )
                Text(text = "Tìm")
            }
        }
    }
}