package com.example.hotelbooking.ui.utility

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hotelbooking.R

@Composable
fun ImportantButtonLogin(text: String, onAction: ()->(Unit), modifier: Modifier = Modifier){
    val gradient = Brush.linearGradient(listOf(Color(0xFF03045E), Color(0xFF0172B2)), start = Offset(0f, Float.POSITIVE_INFINITY), end = Offset(Float.POSITIVE_INFINITY, 0f))


    OutlinedButton(
        modifier = modifier.fillMaxWidth().height(40.dp),
        onClick = onAction,
        border = BorderStroke(2.dp, gradient),
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(
            text = text,
            color = colorResource(R.color.primary),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}