package com.example.hotelbooking.view.homepage

import HotelCard
import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.platform.LocalContext
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hotelbooking.R
import com.example.hotelbooking.navigation.Route
import com.example.hotelbooking.ui.utility.AppBar
import com.example.hotelbooking.ui.utility.ImportantButtonMain
import com.example.hotelbooking.ui.utility.LocationUtil
import com.example.hotelbooking.view.homepage.components.CommonOutlinedButton
import com.example.hotelbooking.view.components.HotelsViewState
import com.example.hotelbooking.view.components.ProfileViewState
import com.example.hotelbooking.view.homepage.components.OutlinedBlock
import com.example.hotelbooking.view.homepage.components.SearchViewState
import com.example.hotelbooking.viewmodel.HotelsViewModel
import com.example.hotelbooking.viewmodel.UsersViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun HomePageSearchScreen(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    hotelsViewModel: HotelsViewModel = hiltViewModel(),
    usersViewModel: UsersViewModel = hiltViewModel(),
    openResultScreen: () -> Unit,
    openRoomScreen: () -> Unit,
    openLocationScreen: () -> Unit,
    openDetailsScreen: (String) -> Unit = {}
) {
    val hotelsState by hotelsViewModel.hotelsState.collectAsStateWithLifecycle()

    val searchState by hotelsViewModel.searchState.collectAsStateWithLifecycle()

    val userState by usersViewModel.state.collectAsStateWithLifecycle()

    val nearHotelsState by hotelsViewModel.nearHotelsState.collectAsStateWithLifecycle()

    val context = LocalContext.current
    val locationUtil = remember { LocationUtil(context) }
    var userLocation by remember { mutableStateOf<Pair<Double, Double>?>(null) }
    var error by remember { mutableStateOf<Exception?>(null) }

    val locationPermissionRequest = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted: Boolean ->
            if (isGranted) {
                locationUtil.getLastUserLocation(
                    onGetLastLocationSuccess = { lat, lon ->
                        userLocation = Pair(lat, lon)
                    },
                    onGetLastLocationFailed = { exception ->
                        error = exception
                    }
                )
            } else {
                Toast.makeText(context, "Location permission denied", Toast.LENGTH_LONG).show()
            }
        }
    )


    LaunchedEffect(Unit) {
        hotelsViewModel.getTopBookings()
        usersViewModel.getUser()
        if (locationUtil.areLocationPermissionsGranted()) {
            locationUtil.getLastUserLocation(
                onGetLastLocationSuccess = { lat, lon ->
                    userLocation = Pair(lat, lon)
                    Log.d("User Location Success", lat.toString())
                    Log.d("user location varible", userLocation.toString())
                    hotelsViewModel.getNearHotels(lon.toString(), lat.toString())
                },
                onGetLastLocationFailed = { exception ->
                    error = exception
                    Log.d("User Location Error", exception.toString())
                }
            )
        } else {
            locationPermissionRequest.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    HomePageSearchContent(
        modifier,
        userState,
        usersViewModel,
        navController,
        nearHotelsState,
        hotelsState,
        searchState,
        hotelsViewModel,
        openResultScreen,
        openRoomScreen,
        openLocationScreen,
        openDetailsScreen
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePageSearchContent(
    modifier: Modifier = Modifier,
    userState: ProfileViewState,
    usersViewModel: UsersViewModel = hiltViewModel(),
    navController: NavController,
    nearHotelsState: HotelsViewState,
    hotelsState: HotelsViewState,
    searchState: SearchViewState,
    hotelsViewModel: HotelsViewModel = hiltViewModel(),
    openResultScreen: () -> Unit,
    openRoomScreen: () -> Unit,
    openLocationScreen: () -> Unit,
    openDetailsScreen: (String) -> Unit = {}
) {
    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    val current = LocalDateTime.now()
    val nextDay = current.plusDays(1)

    var location: String by remember { mutableStateOf(searchState.location) }
    var dateIn: String by remember { mutableStateOf(searchState.checkIn) }
    var dateOut: String by remember { mutableStateOf(searchState.checkOut) }
    var nofRoom: Int by remember { mutableStateOf(searchState.roomCount) }
    var nofGuest: Int by remember { mutableStateOf(searchState.adultCount + searchState.childCount) }

    val calendarState = rememberSheetState()
    var selectedDateType by remember { mutableStateOf<DateType?>(null) }

    val searchParams: HashMap<String, String> = HashMap()

    LaunchedEffect(searchState) {
        location = searchState.location
        dateIn = searchState.checkIn
        dateOut = searchState.checkOut
        nofRoom = searchState.roomCount
        nofGuest = searchState.adultCount + searchState.childCount

        if (dateIn.isNotEmpty()) {
            val checkIn = LocalDate.parse(dateIn, formatter)
            searchParams["checkIn"] = checkIn.toString()
        }

        if (dateOut.isNotEmpty()) {
            val checkOut = LocalDate.parse(dateOut, formatter)
            searchParams["checkOut"] = checkOut.toString()
        }

        searchParams["place_id"] = searchState.location_id
        searchParams["adultCount"] = searchState.adultCount.toString()
        searchParams["childCount"] = searchState.childCount.toString()
        searchParams["roomCount"] = searchState.roomCount.toString()
        searchParams["sortOption"] = searchState.sortOption
        searchParams["page"] = "1"
    }

    LaunchedEffect(dateIn, dateOut) {
        hotelsViewModel.updateSearchParams(checkIn = dateIn, checkOut = dateOut)
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
                    onLocationAction = { openLocationScreen() },
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
                ImportantButtonMain(text = "Tìm kiếm", onClick = {
                    hotelsViewModel.search(searchParams)

                    openResultScreen()
                })
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Dành cho bạn",
                    style = MaterialTheme.typography.titleLarge,
                    color = colorResource(R.color.dark_blue),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            item {

                LazyRow(

                    modifier = Modifier.fillMaxWidth(),

                    horizontalArrangement = Arrangement.spacedBy(8.dp)

                )

                {
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

            item{
                Text(
                    text = "Xung quanh bạn",
                    style = MaterialTheme.typography.titleLarge,
                    color = colorResource(R.color.dark_blue),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {

                LazyRow(

                    modifier = Modifier.fillMaxWidth(),

                    horizontalArrangement = Arrangement.spacedBy(8.dp)

                )

                {
                    items(nearHotelsState.hotels) { hotel ->
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
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBlock(
    location: String, onLocationAction: () -> Unit,
    dateIn: String, onDateInAction: () -> Unit,
    dateOut: String, onDateOutAction: () -> Unit,
    nofRoom: Int,
    nofGuest: Int,
    onBlockAction: () -> Unit,
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