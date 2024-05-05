package com.example.doanmobileui.ui.utility

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun ActionText(text: String, action: ()->(Unit),modifier: Modifier = Modifier){
    Text(
        modifier = modifier,
        text = text,
        fontSize = 16.sp,
        color = Color(0xFF0172B2)
    )
}