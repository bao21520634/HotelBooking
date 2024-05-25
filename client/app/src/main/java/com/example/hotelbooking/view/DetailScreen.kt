package com.example.hotelbooking.view

import StarRatingBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hotelbooking.R
import com.example.hotelbooking.ui.model.Hotel

@Composable
fun DetailScreen(hotel: Hotel){
    Card(
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            DetailThumbNail(hotel = hotel)
            Divider()
            LoaiPhong(hotel = hotel)
            NoiThat(hotel = hotel)
            TienIch(hotel = hotel)
        }
    }
}
@Composable
fun DetailThumbNail(hotel: Hotel, modifier: Modifier = Modifier){
    Column(

    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .width(320.dp),
            painter = painterResource(id = hotel.hotelThumbnail),
            contentDescription = hotel.hotelName,
            contentScale = ContentScale.Crop
        )
        Row(

        ){
            Text(
                text = hotel.hotelName,
                color = Color.Black,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(modifier = modifier.width(4.dp))
            StarRatingBar(rating = hotel.starRating, starsColor = Color(0xFFFFB700));
            Spacer(modifier.weight(1f))
            Text(
                color = colorResource(R.color.primary),
                text = "VND " + hotel.hotelPrice,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF0172B2)
                ),
            ) {
                Text(
                    modifier = Modifier.padding(
                        top = 4.dp,
                        bottom = 4.dp,
                        start = 6.dp,
                        end = 6.dp
                    ),
                    text = hotel.commentRating.toString(),
                    color = Color.White,
                    fontSize = 14.sp
                )
            }

            val ratingToStringID: Int = if (hotel.commentRating > 9) R.string.veryGoodRating
            else if (hotel.commentRating > 8) R.string.goodRating
            else if (hotel.commentRating > 6.5) R.string.okRating
            else if (hotel.commentRating > 6.5) R.string.badRating
            else R.string.veryBadRating
            val ratingToString: String = stringResource(id = ratingToStringID)

            Text(
                text = ratingToString,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                fontSize = 14.sp
            )
            Spacer(Modifier.width(16.dp))
            Text(
                text = hotel.numberOfComment.toString() + " đánh giá",
                color = Color.Black,
                fontSize = 14.sp
            )
        }
    }

}
@Composable
fun LoaiPhong(hotel: Hotel, modifier: Modifier  = Modifier){
    Column {
        Text(
            text = "Loại phòng",
            style = MaterialTheme.typography.titleLarge,
            color = colorResource(R.color.dark_blue),
            fontWeight = FontWeight.Bold,
        )
    }

}
@Composable
fun NoiThat(hotel: Hotel, modifier: Modifier = Modifier){
    Column {
        Text(
            text = "Nội thất",
            style = MaterialTheme.typography.titleLarge,
            color = colorResource(R.color.dark_blue),
            fontWeight = FontWeight.Bold,
        )
    }

}
@Composable
fun TienIch(hotel: Hotel, modifier: Modifier = Modifier){
    Column {
        Text(
            text = "Tiện ích",
            style = MaterialTheme.typography.titleLarge,
            color = colorResource(R.color.dark_blue),
            fontWeight = FontWeight.Bold,
        )
    }

}
@Preview
@Composable
fun DetailScreenPreview(){
    DetailScreen(hotel = Hotel(
        hotelThumbnail = R.drawable.hotel_thumbnail,
        hotelName = "Cityscape Suites",
        starRating = 4.2f,
        commentRating = 8.5f,
        numberOfComment = 220,
        hotelAddress = "789 Downtown Ave, New York, NY",
        numberOfBed = 1,
        hotelPrice = 250,
        isFeatured = true
        )
    )
}
