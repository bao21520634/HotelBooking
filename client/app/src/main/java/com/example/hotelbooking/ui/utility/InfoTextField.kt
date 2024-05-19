package com.example.hotelbooking.ui.utility

import android.hardware.camera2.params.BlackLevelPattern
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InfoTextField(value: String, onValueChange: (String) -> (Unit),
                  promptText: String,
                  modifier: Modifier = Modifier,
                  fontSize: Float = 16f)
{
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(20.dp),
        value = value,
        singleLine = true,
        onValueChange = onValueChange,
        label = {Text(promptText, fontSize = 11.sp)},
        colors = TextFieldDefaults.colors(

        )
    )
}