package com.example.hotelbooking.ui.utility

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hotelbooking.ui.theme.PrimaryColor

@Composable
fun InfoTextField(value: String, onValueChange: (String) -> (Unit),
                  promptText: String,
                  modifier: Modifier = Modifier)
{
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(20.dp),
        value = value,
        singleLine = true,
        onValueChange = onValueChange,
        label = {Text(promptText, fontSize = 14.sp)},
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            cursorColor = PrimaryColor,
            focusedIndicatorColor = PrimaryColor,
            focusedLabelColor = PrimaryColor
        ),
        textStyle = TextStyle(
            textAlign = TextAlign.Left,
            fontSize = 14.sp
        )
    )
}
