import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.hotelbooking.R
import kotlin.math.ceil
import kotlin.math.floor
@Composable
fun StarRatingBar(
    modifier: Modifier = Modifier,
    stars: Int,
    starsColor: Color = Color.Yellow,
) {
    Row(modifier = modifier) {
        repeat(stars) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_star_24),
                contentDescription = null, tint = starsColor
            )
        }
    }
}
@Preview
@Composable
fun RatingPreview() {
    StarRatingBar(stars = 4);
}
@Preview
@Composable
fun TenStarsRatingPreview() {
    StarRatingBar(stars = 10)
}
@Preview
@Composable
fun RatingPreviewFull() {
    StarRatingBar(stars = 5)
}
@Preview
@Composable
fun RatingPreviewWorst() {
    StarRatingBar(stars = 1)
}
@Preview
@Composable
fun RatingPreviewDisabled() {
    StarRatingBar(stars = 0, starsColor = Color.Gray)
}