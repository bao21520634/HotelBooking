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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.hotelbooking.R
import com.example.hotelbooking.domain.model.Hotel

@Composable
fun HotelCard(hotel: Hotel, modifier: Modifier = Modifier, onClick: () -> Unit) {
    var isFavored: Boolean by remember {
        mutableStateOf(false)
    }

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
                HotelTitle(hotelName = hotel.name,
                    starRating = hotel.starRating,
                    isFavored = isFavored,
                    onFavoriteClick = {isFavored = !(isFavored)})
//                HotelPreview(commentRating = 8.4f, numberOfComment = 100)
                HotelInfo(
                    address = hotel.address,
                    numberOfBedroom = hotel.bedrooms.size,
                    price = if (hotel.totalPrice != 0) hotel.totalPrice else hotel.pricePerNightWeekdays
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HotelTitle(
    hotelName: String,
    starRating: Int,
    isFavored: Boolean,
    onFavoriteClick: () -> (Unit),
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = hotelName,
            color = Color.Black,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.width(4.dp))
    }
    StarRatingBar(stars = starRating, starsColor = colorResource(id = R.color.yellow));

    Card(
        colors = CardDefaults.cardColors(
            containerColor =  Color.Transparent,
            contentColor = Color.Red
        ),
        shape = CircleShape,
        onClick = onFavoriteClick
    ){
        if(isFavored){
            Icon(
                painter = painterResource(id = R.drawable.baseline_favorite_24),
                contentDescription = null,
                modifier = Modifier.padding(4.dp)
            )
        }
        else{
            Icon(
                painter = painterResource(id = R.drawable.baseline_favorite_border_24),
                contentDescription = null,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}


@Composable
fun HotelInfo(
    address: String,
    numberOfBedroom: Int,
    price: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        Text(
            text = address,
            color = Color.Black,
            fontSize = 18.sp
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