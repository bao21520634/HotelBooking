package com.example.hotelbooking.ui.utility

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp
import com.example.hotelbooking.R

@Composable
fun ActionText(text: String, action: ()->(Unit),modifier: Modifier = Modifier){
    TextButton(
        modifier = modifier,
        onClick = action
    ){
        Text(text = text,
            fontSize = 16.sp,
            color = colorResource(R.color.primary)
        )
    }
}