package com.example.hotelbooking.ui.utility

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ImportantButtonMain(text: String, onClick: ()->(Unit), modifier: Modifier = Modifier){
    val gradient = Brush.linearGradient(listOf(Color(0xFF03045E), Color(0xFF0172B2)), start = Offset(0f, Float.POSITIVE_INFINITY), end = Offset(Float.POSITIVE_INFINITY, 0f))

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF0172B2)
        ),
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)
    )
    {
        Text(
            text = text,
            fontSize = 24.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
        )
    }

}

@Preview
@Composable
fun preview(){
    ImportantButtonMain(text = "Tìm kiếm", onClick = { /*TODO*/ })
}