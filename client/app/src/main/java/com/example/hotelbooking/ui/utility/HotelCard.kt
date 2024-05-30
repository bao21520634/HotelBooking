import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.hotelbooking.R
import com.example.hotelbooking.domain.model.Hotel

@Composable
fun HotelCard(hotel: Hotel, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(
        modifier = modifier.clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        border = BorderStroke(1.dp, Color.Gray)
    ) {
        Column(
            modifier = modifier.fillMaxWidth()

        ) {
            AsyncImage(
                model = hotel.imageUrls.first(),
                contentDescription = hotel.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .width(320.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                HotelTitle(hotelName = hotel.name, starRating = hotel.starRating)
//                HotelPreview(commentRating = 8.4f, numberOfComment = 100)
                Spacer(modifier = Modifier.weight(1f))
                HotelInfo(
                    address = hotel.address,
                    numberOfBedroom = hotel.bedrooms.size,
                    price = if (hotel.totalPrice != 0) hotel.totalPrice else hotel.pricePerNightWeekdays
                )
            }
        }
    }
}

@Composable
fun HotelTitle(
    hotelName: String,
    starRating: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        Text(
            text = hotelName,
            color = Color.Black,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Spacer(modifier = modifier.width(4.dp))
    }
    StarRatingBar(stars = starRating, starsColor = colorResource(id = R.color.yellow));

}

@Composable
fun HotelPreview(
    commentRating: Float,
    numberOfComment: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF0172B2)
            ),
        ) {
            Text(
                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp, start = 6.dp, end = 6.dp),
                text = commentRating.toString(),
                color = Color.White,
                fontSize = 14.sp
            )
        }

        val ratingToStringID: Int = if (commentRating > 9) R.string.veryGoodRating
        else if (commentRating > 8) R.string.goodRating
        else if (commentRating > 6.5) R.string.okRating
        else if (commentRating > 6.5) R.string.badRating
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
            text = numberOfComment.toString() + " đánh giá",
            color = Color.Black,
            fontSize = 14.sp
        )
    }
}

@Composable
fun HotelInfo(
    address: String,
    numberOfBedroom: Int,
    price: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(bottom = 6.dp)
    ) {
        Row(
            modifier = modifier
        ) {
            Text(
                text = address,
                color = Color.Black,
                fontSize = 18.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Phòng ngủ: " + numberOfBedroom.toString(),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(Modifier.weight(1f))
            Card(
                border = BorderStroke(1.dp, colorResource(R.color.primary)),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent
                ),
            ) {
                Text(
                    modifier = Modifier.padding(24.dp, 6.dp),
                    color = colorResource(R.color.primary),
                    text = "VND " + price,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

        }
    }
}