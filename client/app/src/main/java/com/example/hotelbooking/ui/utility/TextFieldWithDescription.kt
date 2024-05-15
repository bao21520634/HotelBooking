package com.example.hotelbooking.ui.utility

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EditTextField(value: String, onValueChange: (String) -> (Unit),
                  descriptionText: String
                  ,modifier: Modifier = Modifier){
    Column(
        modifier = modifier.fillMaxWidth()
    ){
        Text(
            text = descriptionText,
        )
        Spacer(modifier.width(4.dp))
        TextField(value = value, onValueChange = onValueChange, modifier = Modifier.fillMaxWidth())
    }
}