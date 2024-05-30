package com.example.hotelbooking.view

import StarRatingBar
import android.R.attr.maxLines
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.hotelbooking.R
import com.example.hotelbooking.domain.model.Hotel
import com.example.hotelbooking.domain.model.User
import com.example.hotelbooking.ui.utility.ImportantButtonMain
import com.example.hotelbooking.view.components.HotelViewState
import com.example.hotelbooking.view.components.ProfileViewState
import com.example.hotelbooking.viewmodel.HotelsViewModel
import com.example.hotelbooking.viewmodel.UsersViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun DetailScreen(
    hotelsViewModel: HotelsViewModel = hiltViewModel(),
    usersViewModel: UsersViewModel = hiltViewModel(),
    id: String, backNav: () -> Unit
) {
    val hotelState by hotelsViewModel.hotelState.collectAsStateWithLifecycle()
    val userState by usersViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        usersViewModel.getUser()
        hotelsViewModel.getHotel(id)
    }

        DetailContent(hotelState, userState, backNav = backNav)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailContent(hotelState: HotelViewState, userState: ProfileViewState, backNav: () -> Unit) {
    val context = LocalContext.current

    Scaffold(
        topBar = {}
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
                    .background(Color.Transparent),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            )
            {
                item {
                    DetailThumbNail(userState.user, hotelState.hotel, navigateUp = backNav)
                    Spacer(modifier = Modifier.padding(top = 8.dp))
                    Divider(color = colorResource(R.color.neutral), thickness = 1.dp)
                }
                item { AddressHotel(hotelState.hotel) }
                item { RoomType(hotelState.hotel) }
                item { Interior(hotelState.hotel) }
                item { Facilities(hotelState.hotel) }
                item { Description(hotelState.hotel) }
                item { Spacer(modifier = Modifier.height(64.dp)) }
            }
            // Switch to URL
            ImportantButtonMain(
                text = "Đặt ngay", onClick = {
                    val url = "https://www.google.com"
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(12.dp)
                    .fillMaxWidth()
                    .height(56.dp)
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailThumbNail(user: User, hotel: Hotel, modifier: Modifier = Modifier, navigateUp: () -> Unit) {
    Column(

    ) {

        Box {
            ImageSlideshow(hotel = hotel)
            // Back to homescreen
            IconButton(
                onClick = navigateUp,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
        Spacer(modifier = modifier.height(10.dp))
        Row(
        ) {
            StarRatingBar(
                stars = hotel.starRating,
                starsColor = Color(0xFFFFB700),
                modifier = Modifier.weight(2f)
            );

            Card(
                modifier = Modifier.padding(end = 0.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent,
                    contentColor = colorResource(id = R.color.red)
                ),
                shape = CircleShape,
//                onClick = onFavoriteClick
            ) {
                if (false) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_favorite_24),
                        contentDescription = null,
                        modifier = Modifier
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_favorite_border_24),
                        contentDescription = null,
                        modifier = Modifier
                    )
                }
            }
        }
        Spacer(modifier = modifier.height(6.dp))
        Row(
        ) {
            Text(
                text = hotel.name,
                color = Color.Black,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(2f)

            )
            Text(
                color = colorResource(R.color.primary),
                text = "VND " + hotel.pricePerNightWeekdays,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun AddressHotel(hotel: Hotel, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Địa chỉ",
            style = MaterialTheme.typography.titleMedium,
            color = colorResource(R.color.dark_blue),
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = "- ${hotel.address} ",
            style = TextStyle(fontSize = 15.sp)
        )
        Text(
            text = "- ${hotel.city}",
            style = TextStyle(fontSize = 15.sp)
        )
    }
}

@Composable
fun RoomType(hotel: Hotel, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Loại phòng",
            style = MaterialTheme.typography.titleMedium,
            color = colorResource(R.color.dark_blue),
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = "- ${hotel.type}",
            style = TextStyle(fontSize = 15.sp)
        )
    }
}

@Composable
fun Interior(hotel: Hotel, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Nội thất",
            style = MaterialTheme.typography.titleMedium,
            color = colorResource(R.color.dark_blue),
            fontWeight = FontWeight.Bold,
        )
        val interior = hotel.interior
        val halfIndex = interior.size / 2 + 1
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                interior.subList(0, halfIndex).forEach { item ->
                    Text(text = "- $item", style = TextStyle(fontSize = 15.sp))
                }
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                interior.subList(halfIndex, interior.size).forEach { item ->
                    Text(text = "- $item", style = TextStyle(fontSize = 15.sp))
                }
            }
        }
    }

}

@Composable
fun Facilities(hotel: Hotel, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Tiện ích",
            style = MaterialTheme.typography.titleMedium,
            color = colorResource(R.color.dark_blue),
            fontWeight = FontWeight.Bold,
        )
        val facilities = hotel.facilities
        val halfIndex = facilities.size / 2 + 1
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                facilities.subList(0, halfIndex).forEach { item ->
                    Text(text = "- $item", style = TextStyle(fontSize = 15.sp))
                }
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                facilities.subList(halfIndex, facilities.size).forEach { item ->
                    Text(text = "- $item", style = TextStyle(fontSize = 15.sp))
                }
            }
        }
    }
}

@Composable
fun Description(hotel: Hotel, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Mô tả",
            style = MaterialTheme.typography.titleMedium,
            color = colorResource(R.color.dark_blue),
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = "${hotel.description} ",
            style = TextStyle(fontSize = 15.sp)
        )
    }
}

@Composable
fun ImageSlideshow(hotel: Hotel, modifier: Modifier = Modifier) {
    var currentIndex by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        AsyncImage(
            model = hotel.imageUrls[currentIndex],
            contentDescription = hotel.name,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

        // Back
        IconButton(
            onClick = {
                currentIndex = if (currentIndex > 0) currentIndex - 1 else hotel.imageUrls.size - 1
            },
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "Previous",
                tint = Color.Gray,
                modifier = Modifier.size(40.dp)
            )
        }

        // Next
        IconButton(
            onClick = {
                currentIndex = (currentIndex + 1) % hotel.imageUrls.size
            },
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Next",
                tint = Color.Gray,
                modifier = Modifier.size(40.dp)
            )
        }
    }
}


