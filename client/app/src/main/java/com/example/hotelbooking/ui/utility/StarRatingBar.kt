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
    rating: Float = 0.0f,
    stars: Int = 5,
    starsColor: Color = Color.Yellow,
) {
    val filledStars = floor(rating).toInt()
    val unfilledStars = (stars - ceil(rating)).toInt()
    val halfStar = (rating.rem(1).equals(0.0))
    Row(modifier = modifier) {
        repeat(filledStars) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_star_24),
                contentDescription = null, tint = starsColor
            )
        }
        if (halfStar) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_star_half_24),
                contentDescription = null,
                tint = starsColor,
            )
        }
        repeat(unfilledStars) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_star_outline_24),
                contentDescription = null,
                tint = starsColor,
            )
        }
    }
}
@Preview
@Composable
fun RatingPreview() {
    StarRatingBar(rating = 4f, stars =  5);
}
@Preview
@Composable
fun TenStarsRatingPreview() {
    StarRatingBar(stars = 10, rating = 8.5f)
}
@Preview
@Composable
fun RatingPreviewFull() {
    StarRatingBar(rating = 5.0f)
}
@Preview
@Composable
fun RatingPreviewWorst() {
    StarRatingBar(rating = 1.0f)
}
@Preview
@Composable
fun RatingPreviewDisabled() {
    StarRatingBar(rating = 0.0f, starsColor = Color.Gray)
}