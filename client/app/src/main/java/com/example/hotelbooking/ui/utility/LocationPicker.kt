package com.example.hotelbooking.ui.utility

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp
import com.example.hotelbooking.R

@Composable
fun LocationPicker(text: String, action: ()->(Unit),modifier: Modifier = Modifier){
    Text(
        modifier = modifier,
        text = text,
        fontSize = 16.sp,
        color = colorResource(R.color.primary)
    )
}