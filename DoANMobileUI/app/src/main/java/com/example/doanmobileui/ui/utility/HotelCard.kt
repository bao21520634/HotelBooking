import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.doanmobileui.ui.model.Hotel
import com.example.doanmobileui.ui.theme.DoANMobileUITheme
import com.example.doanmobileui.R
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HotelCard(hotel: Hotel, modifier: Modifier = Modifier){
    Card(
        modifier = modifier
    ){
        Column(
            modifier = modifier.fillMaxWidth()

        ){
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .width(320.dp),
                painter = painterResource(id = hotel.hotelThumbnail),
                contentDescription = hotel.hotelName,
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){
                HotelTitle(hotelName = hotel.hotelName, starRating = hotel.starRating)
                HotelPreview(commentRating = 8.4f, numberOfComment = 100)
                HotelInfo(address = hotel.hotelAddress, numberOfBed = 1, price = 1234567)
            }
        }
    }
}
@Composable
fun HotelTitle(
    hotelName: String,
    starRating: Float,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier
    ){
        Text(
            text = hotelName,
            color = Color.Black,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Spacer(modifier = modifier.width(4.dp))
        StarRatingBar(rating = starRating, starsColor = Color(0xFFFFB700));
    }
}
@Composable
fun HotelPreview(
    commentRating: Float,
    numberOfComment: Int,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ){
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF0172B2)
            ),
        ){
            Text(
                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp, start = 6.dp, end = 6.dp),
                text = commentRating.toString(),
                color = Color.White,
                fontSize = 14.sp
            )
        }

        val ratingToStringID: Int = if(commentRating>9) R.string.veryGoodRating
        else if(commentRating>8) R.string.goodRating
        else if(commentRating>6.5) R.string.okRating
        else if(commentRating>6.5) R.string.badRating
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
            text = numberOfComment.toString()+" đánh giá",
            color = Color.Black,
            fontSize = 14.sp
        )
    }
}
@Composable
fun HotelInfo(
    address: String,
    numberOfBed: Int,
    price: Int,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier
    ){
        Text(
            text = address,
            color = Color.Black,
            fontSize = 18.sp
        )
    }
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ){
        Text(
            text = "Phòng " + numberOfBed.toString() + " giường",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Spacer(Modifier.weight(1f))
        Card(
            border = BorderStroke(5.dp,Color(0xFF0172B2)),
        ){
            Text(
                modifier = Modifier.padding(8.dp),
                color = Color(0xFF0172B2),
                text = "VND " + price,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }

    }
}
@Preview(showBackground = true)
@Composable
fun HotelCardPreview() {
    HotelCard(Hotel(
        hotelThumbnail = R.drawable.hotel_thumbnail,
        hotelName = "Saigon Hotel",
        starRating = 4f,
        commentRating = 8.4f,
        numberOfComment = 100,
        hotelAddress = "Thủ Đức, Thành phố Hồ Chí Minh",
        numberOfBed = 1,
        hotelPrice = 1234567,
        isFeatured = false
    ))
}