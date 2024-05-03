package com.example.hotelbooking.ui.utility

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InfoTextField(value: String, onValueChange: (String) -> (Unit),
                  promptText: String,
                  modifier: Modifier = Modifier)
{
    OutlinedTextField(
        modifier = modifier.fillMaxWidth().height(50.dp),
        shape = RoundedCornerShape(20.dp),
        value = value,
        singleLine = true,
        onValueChange = onValueChange,
        label = {Text(promptText)}
    )
}