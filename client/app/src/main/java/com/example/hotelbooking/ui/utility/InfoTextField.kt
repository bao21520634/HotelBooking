package com.example.hotelbooking.ui.utility

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hotelbooking.R
import com.example.hotelbooking.ui.theme.PrimaryColor

@Composable
fun InfoTextField(
    modifier: Modifier = Modifier,
    value: String, onValueChange: (String) -> (Unit),
    promptText: String, fontSize: TextUnit = 14.sp,
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(20.dp),
        value = value,
        singleLine = true,
        onValueChange = onValueChange,
        label = { Text(promptText, fontSize = fontSize) },
        colors = TextFieldDefaults.colors(
            focusedTextColor = colorResource(id = R.color.black),
            unfocusedTextColor = colorResource(id = R.color.black),
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            cursorColor = colorResource(id = R.color.primary),
            focusedIndicatorColor = colorResource(id = R.color.primary),
            focusedLabelColor = colorResource(id = R.color.primary),
        ),
        textStyle = TextStyle(
            textAlign = TextAlign.Left,
            fontSize = 14.sp
        )
    )
}
